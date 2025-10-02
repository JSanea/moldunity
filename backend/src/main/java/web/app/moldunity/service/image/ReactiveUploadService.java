package web.app.moldunity.service.image;

import reactor.core.publisher.Mono;

import java.io.File;

public interface ReactiveUploadService {
    Mono<String> upload(Long adId,  File file);
}
