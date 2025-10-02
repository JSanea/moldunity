package web.app.moldunity.util;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FilePartUtil {
    public static Mono<File> filePartToFile(FilePart filePart) {
        return Mono.defer(() -> Mono.fromCallable(() -> File.createTempFile("upload-", "-" + filePart.filename()))
                .flatMap(tempFile ->
                        filePart.transferTo(tempFile)
                                .thenReturn(tempFile)
                )).subscribeOn(Schedulers.boundedElastic())
                .onErrorMap(IOException.class, e -> new RuntimeException("Failed to create temp file or transfer data", e));
    }

    public static Mono<List<File>> filePartsToFiles(List<FilePart> fileParts){
        return Flux.fromIterable(fileParts)
                .flatMap(FilePartUtil::filePartToFile)
                .collectList();
    }
}
