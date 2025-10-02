package web.app.moldunity.service.image.s3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import web.app.moldunity.service.image.ReactiveUploadService;

import java.io.File;
import java.util.UUID;


@Primary
@Service
@RequiredArgsConstructor
@Slf4j
public class S3UploadService implements ReactiveUploadService {
    @Value("${aws.s3.bucket}")
    private String bucket;
    private final S3AsyncClient asyncS3Client;

    @Override
    public Mono<String> upload(Long adId, File file){
        String key = "ads/" + adId + "/" + UUID.randomUUID() + "_" + file.getName();
        return Mono.fromFuture(asyncS3Client.putObject(
                getPutObjectRequest(key),
                AsyncRequestBody.fromFile(file))
        )
        .thenReturn("https://" + bucket + ".s3.amazonaws.com/" + key)
        .onErrorResume(e -> {
            log.error("Failed to upload file to S3: {}", e.getMessage(), e);
            return Mono.error(new RuntimeException("Failed to upload file to S3", e));
        });
    }

    private PutObjectRequest getPutObjectRequest(String key){
        return PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType("image/webp")
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();
    }
}




