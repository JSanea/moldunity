package web.app.moldunity.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import static web.app.moldunity.util.ThreadPoolAndHikariPoolSize.*;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private final Integer QUEUE_CAPACITY = 512;

    @Override
    @Bean
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(getPoolSize());
        executor.setMaxPoolSize(getMaxPoolSize());
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix("async_");
        executor.initialize();
        return executor;
    }
}














