package web.app.moldunity.service.image;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.entity.postgres.ad.AdImage;
import web.app.moldunity.service.AdService;

import java.io.File;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class ImageService {
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
}

















