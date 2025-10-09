package web.app.moldunity.service.image;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.entity.postgres.ad.AdImage;
import web.app.moldunity.event.AdImagesDeleteAllEvent;

@Service
@AllArgsConstructor
@Slf4j
public class AdImageService {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Mono<Long> deleteAll(Long adId){
        return r2dbcEntityTemplate.delete(Query.query(Criteria.where("ad_id").is(adId)), AdImage.class)
                .onErrorResume(e -> {
                    log.error("Error delete image paths from database: {}", e.getMessage(), e);
                    return Mono.error(new RuntimeException("Error delete image paths from database"));
                });
    }

    @EventListener
    public Mono<Long> deleteAllEvent(AdImagesDeleteAllEvent deleteAllEvent){
        return deleteAll(deleteAllEvent.adId());
    }
}
