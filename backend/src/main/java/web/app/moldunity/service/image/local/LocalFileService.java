package web.app.moldunity.service.image.local;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import web.app.moldunity.service.image.ReactiveFileService;

import java.io.File;


@Service
@Slf4j
public class LocalFileService implements ReactiveFileService {
    @Override
    public Mono<String> upload(Long adId, File file) {
        return Mono.defer(() -> Mono.fromCallable(() -> {
            String storageDir = "/home/alx/Pictures/ads/" + adId;
            String originalFilename = file.getName();
            String newFilename = "ads_" + adId + "_" + originalFilename;

            java.nio.file.Files.createDirectories(java.nio.file.Paths.get(storageDir));

            File destination = new File(storageDir, newFilename);

            java.nio.file.Files.copy(file.toPath(), destination.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            return destination.getAbsolutePath();
        }).subscribeOn(Schedulers.boundedElastic()));
    }

    @Override
    public Mono<Void> deleteAll(String prefix) {
        return Mono.empty();
    }
}
