package web.app.moldunity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import web.app.moldunity.dto.ad.*;
import web.app.moldunity.entity.postgres.ad.Ad;
import web.app.moldunity.entity.postgres.ad.AdImage;
import web.app.moldunity.entity.postgres.ad.FavoriteAd;
import web.app.moldunity.entity.postgres.ad.Subcategory;
import web.app.moldunity.exception.AdServiceException;
import web.app.moldunity.exception.InvalidAdStructureException;
import web.app.moldunity.filter.EntityFilter;
import web.app.moldunity.filter.FilterMap;
import web.app.moldunity.filter.FilterQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdService {
    @Value("${page.limit}")
    private Long limit;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final DatabaseClient databaseClient;
    private final TransactionalOperator tx;
    private final FilterMap adFilter;
    private UserService userService;

    @Autowired
    public void setUserService(@Lazy UserService userService){
        this.userService = userService;
    }

    public <S extends Subcategory> Mono<AdDetailsWithImages> getById(Long id, Class<S> subcategoryType) {
        return r2dbcEntityTemplate.selectOne(Query.query(Criteria.where("id").is(id)), Ad.class)
                .flatMap(ad -> {
                    Mono<? extends Subcategory> subcategoryMono = r2dbcEntityTemplate
                            .selectOne(Query.query(Criteria.where("ad_id").is(ad.getId())), subcategoryType);

                    Mono<List<AdImage>> imagesMono = r2dbcEntityTemplate
                            .select(Query.query(Criteria.where("ad_id").is(ad.getId())), AdImage.class)
                            .collectList();

                    Mono<Boolean> favoriteMono = isFavorite(id);

                    return Mono.zip(subcategoryMono, imagesMono, favoriteMono)
                            .map(tuple -> {
                                Subcategory subcategory = tuple.getT1();
                                List<AdImage> images = tuple.getT2();
                                Boolean favorite = tuple.getT3();

                                AdWithImages adWithImages = new AdWithImages(ad, images, favorite);
                                return new AdDetailsWithImages(adWithImages, subcategoryType.cast(subcategory));
                            });
                })
                .onErrorResume(e -> {
                    log.error("Error to get Ad by id: {}", e.getMessage(), e);
                    //return Mono.error(new AdServiceException("Failed to get Ad by id"));
                    return Mono.empty();
                });
    }

    public <S extends Subcategory> Mono<S> findSubcategoryByAdId(Long adId, Class<S> subcategory){
        return r2dbcEntityTemplate.selectOne(
                Query.query(Criteria.where("ad_id").is(adId)),
                subcategory
        )
        .onErrorResume(e -> {
            log.error("Error to get subcategory by Ad id: {}", e.getMessage(), e);
            //return Mono.error(new AdServiceException("Failed to get Ad by id"));
            return Mono.empty();
        });
    }

    public Mono<AdPage> findBySubcategoryAndFilter(String subcategory, Long page, Map<String, List<String>> filter){
        EntityFilter filterHandler = adFilter.getFilter(subcategory);
        FilterQuery fq = filterHandler.filter(filter);;
        DatabaseClient.GenericExecuteSpec execute;
        DatabaseClient.GenericExecuteSpec countExec;
        String sql = baseSelectAds() + fq.sql() + "LIMIT :limit OFFSET :offset ";

        if(filter == null || filter.isEmpty()){
            execute = databaseClient.sql(sql).bind("subcategory", subcategory);
            countExec = databaseClient.sql(fq.countSql()).bind("subcategory", subcategory);
        }else{
            execute = databaseClient.sql(sql).bindValues(fq.params());
            countExec = databaseClient.sql(fq.countSql()).bindValues(fq.params());
        }

        log.debug("Params: {}", fq.params());
        log.debug("SQL: {}", sql);

        return execute
                .bind("limit", limit)
                .bind("offset", limit * (Math.max(page, 1L) - 1))
                .map(((row, rowMetadata) -> Ad.mapRowToAd(row)))
                .all()
                .collectList()
                .flatMap(ads -> {
                    if (ads.isEmpty()) {
                        return Mono.just(new AdPage(List.of(), 0L, page));
                    }

                    List<Long> adIds = ads.stream().map(Ad::getId).toList();

                    return databaseClient.sql(String.format(
                            """
                            %1$s
                            WHERE ad_id IN (:ids)
                            """, baseSelectImages())
                            )
                            .bind("ids", adIds)
                            .map((row, metadata) -> AdImage.mapRowToAdImage(row))
                            .all()
                            .collectList()
                            .flatMap(images -> findFavoriteIds()
                                    .flatMap(favoriteIds -> {
                                        Map<Long, List<AdImage>> groupedImages = images.stream()
                                                .collect(Collectors.groupingBy(AdImage::getAdId));

                                        List<AdWithImages> adWithImages = new ArrayList<>();
                                        for (Ad ad : ads) {
                                            adWithImages.add(new AdWithImages(
                                                    ad,
                                                    groupedImages.getOrDefault(ad.getId(), List.of()),
                                                    favoriteIds.contains(ad.getId())));
                                        }
                                        Mono<Long> count = countExec
                                                .bindValues(fq.params())
                                                .map((row, meta) -> row.get(0, Long.class))
                                                .one();

                                        return Mono.zip(Mono.just(adWithImages), count)
                                                .map(tuple -> new AdPage(tuple.getT1(), tuple.getT2(), page));
                                    }));
                });
    }

    public <S extends Subcategory> Mono<AdDetails> save(AdDetails adDetails, Class<S> subcategoryType) {
        adDetails.ad().setDateTimeFields();
        return r2dbcEntityTemplate.insert(Ad.class).using(adDetails.ad())
                .flatMap(savedAd -> {
                    S subcategory = subcategoryType.cast(adDetails.subcategory());

                    if (null == subcategory) return Mono.error(new InvalidAdStructureException("Ad subcategory must not be null"));

                    subcategory.setAdId(savedAd.getId());

                    return r2dbcEntityTemplate.insert(subcategoryType).using(subcategory)
                            .flatMap(savedSubcategory -> Mono.just(new AdDetails(savedAd, savedSubcategory)));
                })
                .as(tx::transactional)
                .onErrorResume(e -> {
                    log.error("Error inserting Ad: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Failed to insert Ad"));
                });
    }

    public <S extends Subcategory> Mono<Ad> update(AdDetails adDetails, Class<S> subcategory) {
        return r2dbcEntityTemplate.select(Ad.class)
                .matching(Query.query(Criteria.where("id").is(adDetails.ad().getId())))
                .one()
                .switchIfEmpty(Mono.error(new AdServiceException("Ad with id " + adDetails.ad().getId() + " not found")))
                .flatMap(existingAd ->
                        r2dbcEntityTemplate.update(existingAd.update(adDetails.ad()))
                                .flatMap(updatedAd -> {
                                    S s = subcategory.cast(adDetails.subcategory());
                                    return r2dbcEntityTemplate.update(s).map(u -> updatedAd);
                                })
                )
                .as(tx::transactional)
                .onErrorResume(e -> {
                    log.error("Error updating Ad: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Failed to update Ad"));
                });
    }

    public Mono<Ad> delete(Long adId) {
        return r2dbcEntityTemplate.select(Ad.class)
                .matching(Query.query(Criteria.where("id").is(adId)))
                .one()
                .switchIfEmpty(Mono.error(new AdServiceException("Ad with id " + adId + " not found")))
                .flatMap(existingAd ->
                        r2dbcEntityTemplate.delete(existingAd)
                                .map(u -> existingAd)
                )
                .as(tx::transactional)
                .onErrorResume(e -> {
                    log.error("Error deleting Ad: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Failed to delete Ad"));
                });
    }

    public Mono<List<Long>> findFavoriteIds(){
        return userService.getUser()
                .flatMap(user -> {
                    if (user == null || user.getId() == null) {
                        return Mono.just(List.of(0L));
                    }
                    return findFavoriteIdsByUserId(user.getId());
                })
                .onErrorResume(e -> {
                    log.error("Error to get favorite ids: {}", e.getMessage(), e);
                    return Mono.just(List.of());
                });
    }

    public Mono<List<Long>> findFavoriteIdsByUserId(Long userId){
        return r2dbcEntityTemplate.select(
                Query.query(Criteria.where("user_id").is(userId)),
                FavoriteAd.class
        )
        .map(FavoriteAd::getId)
        .collectList();
    }

    public Mono<Ad> republish(Long adId){
        return r2dbcEntityTemplate.select(Ad.class)
                .matching(Query.query(Criteria.where("id").is(adId)))
                .one()
                .switchIfEmpty(Mono.error(new AdServiceException("Ad with id " + adId + " not found")))
                .flatMap(existingAd -> {
                    if(!existingAd.getRepublishedAt().toLocalDate().equals(LocalDate.now())){
                        existingAd.setRepublishedAt(LocalDateTime.now());
                        return r2dbcEntityTemplate.update(existingAd).map(ad -> existingAd);
                    }
                    return Mono.empty();
                })
                .as(tx::transactional)
                .onErrorResume(e -> {
                    log.error("Error updating Ad: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Failed to republish Ad"));
                });
    }

    public Flux<AdWithImages> findByUsername(String username){
        String sql = selectAdsWithImages() +
                """
                WHERE ads.username = :username
                """;

        return findAdsWithImagesByCondition(databaseClient.sql(sql)
                .bind("username", username));
    }

    public Mono<List<AdWithImages>> getByUsername(String username) {
        return findByUsername(username).collectList();
    }

    public Mono<Long> getCountAdsByUsername(String username) {
        return databaseClient.sql("SELECT count(*) AS cnt FROM ads WHERE username = :username")
                .bind("username", username)
                .map((row, metadata) -> row.get("cnt", Long.class))
                .one()
                .onErrorResume(e -> {
                    log.error("Error counting Ads by username: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Failed to count Ads by username"));
                });
    }

    public Mono<Long> getCountAdsByUsernameAndSubcategory(String username, String subcategory) {
        return databaseClient.sql("SELECT count(*) AS cnt FROM ads WHERE username = :username AND subcategory_name = :subcategory")
                .bind("username", username)
                .bind("subcategory", subcategory)
                .map((row, metadata) -> row.get("cnt", Long.class))
                .one()
                .onErrorResume(e -> {
                    log.error("Error counting Ads by username: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Failed to count Ads by username"));
                });
    }

    public Mono<String> getOwnerById(Long id){
        return databaseClient.sql("SELECT ads.username FROM ads WHERE ads.id = :id")
                .bind("id", id)
                .map(((row, rowMetadata) -> row.get("username", String.class)))
                .one()
                .onErrorResume(e -> {
                    log.error("Error to get Ad owner: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Error to get Ad owner"));
                });
    }

    public Mono<Long> countBySubcategory(String subcategory) {
        String sql = "SELECT count(*) FROM ads WHERE subcategory_name = :subcategory";
        return databaseClient.sql(sql)
                .bind("subcategory", subcategory)
                .map(row -> row.get(0, Long.class))
                .one();
    }

    public Mono<Long> countImages(Long adId){
        String sql = "SELECT count(ads.id) FROM ads " +
                "INNER JOIN ad_images ON ads.id = ad_images.ad_id " +
                "WHERE ads.id = :id";

        return databaseClient.sql(sql)
                .bind("id", adId)
                .map(row -> row.get(0, Long.class))
                .one();
    }

    public Mono<AdImage> saveImageUrl(AdImage image) {
        return r2dbcEntityTemplate
                .insert(image)
                .as(tx::transactional)
                .onErrorResume(e -> {
                    log.error("Error saving image url: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Failed to save image url"));
                });
    }

    public Mono<Boolean> isOwner(Long adId) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(auth -> getOwnerById(adId).map(owner -> owner.equals(auth.getPrincipal())))
                .onErrorResume(e -> {
                    log.error("Error to check Ad owner: {}", e.getMessage(), e);
                    return Mono.error(new AdServiceException("Error to check Ad owner"));
                });
    }

    public Mono<Boolean> isFavorite(Long adId){
        return userService.getUser()
                .flatMap(user -> {
                    if (user == null || user.getId() == null) {
                        return Mono.just(false);
                    }
                    return r2dbcEntityTemplate.exists(Query.query(Criteria.where("ad_id").is(adId)
                            .and(Criteria.where("user_id").is(user.getId()))), FavoriteAd.class);
                })
                .onErrorResume(e -> {
                    log.error("Error checking favorite status for adId {}: {}", adId, e.getMessage(), e);
                    return Mono.error(new RuntimeException("Error checking favorite status for adId"));
                });
    }

    private String selectAdsWithImages(){
        return String.format("""
                %1$s
                LEFT JOIN ad_images ON ad_images.ad_id = ads.id
                """, baseSelectAdsWithImages());
    }

    private Flux<AdWithImages> findAdsWithImagesByCondition(DatabaseClient.GenericExecuteSpec executeSpec) {
        return executeSpec.map((row, metadata) -> {
                    Ad ad = Ad.mapRowToAd(row);
                    AdImage adImage = AdImage.mapRowToAdImage(row);
                    return new AdWithImage(ad, adImage);
                })
                .all()
                .collectMultimap(a -> a.ad().getId())
                .flatMapMany(map -> Flux.fromIterable(map.entrySet()))
                .map(entry -> {
                    List<AdWithImage> adWithImages = new ArrayList<>(entry.getValue());
                    Ad ad = adWithImages.get(0).ad();

                    List<AdImage> images = entry.getValue().stream()
                            .map(AdWithImage::adImage)
                            .filter(adImage -> adImage.getId() != null && adImage.getUrl() != null)
                            .toList();

                    return new AdWithImages(ad, images, false);
                })
                .flatMap(a -> isFavorite(a.ad().getId())
                        .map(result -> new AdWithImages(a.ad(), a.adImages(), result)))
                .onErrorResume(e -> {
                    log.error("Error fetching Ads: {}", e.getMessage(), e);
                    return Flux.error(new AdServiceException("Failed to fetch Ads"));
                });
    }

    private String baseSelectAdsWithImages(){
        return """
                SELECT ads.id AS ads_id, ads.username, ads.offer_type, ads.title, ads.category_name, ads.subcategory_name,
                   ads.country, ads.location, ads.description, ads.price, ads.created_at AS ads_created_at, ads.updated_at,
                   ads.republished_at, ads.user_id,
                   ad_images.id AS ad_images_id, ad_images.url AS ad_images_url, ad_images.created_at AS ad_images_created_at,
                   ad_images.ad_id AS ad_images_ad_id
                   FROM ads
                """;
    }

    private String baseSelectAds(){
        return """
                SELECT ads.id AS ads_id, ads.username, ads.offer_type, ads.title, ads.category_name, ads.subcategory_name,
                   ads.country, ads.location, ads.description, ads.price, ads.created_at AS ads_created_at, ads.updated_at,
                   ads.republished_at, ads.user_id
                FROM ads
                """;
    }

    private String baseSelectImages(){
        return """
                SELECT
                ad_images.id AS ad_images_id, ad_images.url AS ad_images_url,
                ad_images.created_at AS ad_images_created_at,
                ad_images.ad_id AS ad_images_ad_id
            FROM ad_images
            """;
    }
}



