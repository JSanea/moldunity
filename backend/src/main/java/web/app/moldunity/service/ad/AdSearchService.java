package web.app.moldunity.service.ad;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.model.dto.ad.AdPage;
import web.app.moldunity.model.entity.postgres.ad.Ad;
import web.app.moldunity.service.data.ReactiveDataManager;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdSearchService {
    @Value("${page.limit}")
    private Long limit;
    private final AdService adService;
    private final ReactiveDataManager dataManager;

    public Mono<AdPage> search(String query, Long page){
        String sql = String.format("""
                %1$s
                WHERE search_vector @@ to_tsquery('simple', :query)
                ORDER BY ads.republished_at DESC
                LIMIT :limit OFFSET :offset;
                """, adService.baseSelectAds());

        String cntSql = """
                SELECT COUNT(ads.id) FROM ads
                WHERE search_vector @@ to_tsquery('simple', :query);
                """;

        Mono<List<Ad>> ads = dataManager.databaseClient().sql(sql)
                        .bind("query", query)
                        .bind("limit", limit)
                        .bind("offset", limit * (Math.max(page, 1L) - 1))
                        .map(((row, rowMetadata) -> Ad.mapRowToAd(row)))
                        .all()
                        .collectList();

        Mono<Long> count = dataManager.databaseClient().sql(cntSql)
                .bind("query", query)
                .map((row, rowMetadata) -> row.get(0, Long.class))
                .one();

        return adService.createAdPage(ads, count, page);
    }
}














