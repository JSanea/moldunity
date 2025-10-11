package web.app.moldunity.service.image.s3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.model.entity.postgres.ad.AdImage;
import web.app.moldunity.event.S3AdImagesDeleteAllEvent;
import web.app.moldunity.service.AdService;
import web.app.moldunity.service.image.ReactiveFileService;

import java.io.File;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class S3ImageService {
    private final ReactiveFileService fileService;
    private final AdService adService;

    public Mono<AdImage> save(Long id, File file){
        return fileService.upload(id, file)
                .flatMap(url -> adService.saveImageUrl(AdImage.builder()
                        .url(url)
                        .createdAt(LocalDateTime.now())
                        .adId(id)
                        .build()))
                .doFinally(signalType -> {
                    if(!file.delete()){
                        log.warn("Failed to delete temp input file: {}", file.getAbsolutePath());
                        file.deleteOnExit();
                    }
                });
    }

    public Mono<Void> delete(String url){
        return Mono.empty();
    }

    public Mono<Void> deleteAll(Long adId){
        String prefix = "ads/" + adId + "/";
        return fileService.deleteAll(prefix);
    }

    @EventListener
    public Mono<Void> deleteAllEvent(S3AdImagesDeleteAllEvent deleteAllEvent){
        return deleteAll(deleteAllEvent.adId());
    }
}

















