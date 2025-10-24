package web.app.moldunity.controller.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import web.app.moldunity.event.AdImagesDeleteAllEvent;
import web.app.moldunity.event.S3AdImagesDeleteAllEvent;
import web.app.moldunity.service.ad.AdService;
import web.app.moldunity.service.image.ImageConvertService;
import web.app.moldunity.service.image.s3.S3ImageService;
import web.app.moldunity.util.FilePartUtil;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api")
public class ImageController {
    @Value("${images.limit}")
    private Long limit;
    private final S3ImageService imageService;
    private final ImageConvertService imageConvertService;
    private final AdService adService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping(value = "/images/ads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<String>> save(@PathVariable Long id,
                                             @RequestPart("images") List<FilePart> images){
        long size = images.size();

        if (images.isEmpty() || size > limit) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        }

        return adService.countImages(id)
                .flatMap(cnt -> cnt >= limit || size > limit - cnt
                        ? Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image limit exceeded"))
                        : FilePartUtil.filePartsToFiles(images))
                .flatMap(imageConvertService::convertImages)
                .flatMapMany(Flux::fromIterable)
                .flatMap(webp -> imageService.save(id, webp))
                .then(Mono.just(ResponseEntity.ok("Files uploaded successfully")))
                .onErrorResume(e -> {
                    if (e instanceof ResponseStatusException ex) {
                        return  Mono.just(ResponseEntity.status(ex.getStatusCode()).body(ex.getReason()));
                    }
                    log.error("Error uploading images: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @DeleteMapping(value = "/images/ads/{id}")
    public Mono<ResponseEntity<String>> deleteAll(@PathVariable Long id){
        return Mono.fromRunnable(() -> {
                    eventPublisher.publishEvent(new S3AdImagesDeleteAllEvent(id));
                    eventPublisher.publishEvent(new AdImagesDeleteAllEvent(id));
                })
                .thenReturn(ResponseEntity.ok("Ok"))
                .onErrorResume(e -> {
                    log.error("Failed to delete all images: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                 });
    }
}

















