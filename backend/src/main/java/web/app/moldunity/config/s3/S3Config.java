package web.app.moldunity.config.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;
import java.time.Duration;

@Configuration
public class S3Config {
    @Value("${aws.s3.region}")
    private String region;
    @Value("${aws.s3.access-key}")
    private String accessKey;
    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Bean
    public S3AsyncClient s3AsyncClient() {
        SdkAsyncHttpClient sdkAsyncHttpClient = NettyNioAsyncHttpClient.builder()
                .maxConcurrency(32)
                .connectionAcquisitionTimeout(Duration.ofSeconds(5))
                .connectionTimeout(Duration.ofSeconds(5))
                .maxPendingConnectionAcquires(32)
                .tcpKeepAlive(true)
                .build();

        S3Configuration s3Configuration = S3Configuration.builder()
                .chunkedEncodingEnabled(true)
                .build();

        return S3AsyncClient.builder()
                .httpClient(sdkAsyncHttpClient)
                .serviceConfiguration(s3Configuration)
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}
