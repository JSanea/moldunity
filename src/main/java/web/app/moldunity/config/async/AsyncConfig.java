package web.app.moldunity.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.concurrent.Executor;

import static web.app.moldunity.util.ThreadPoolAndHikariPoolSize.getMaxPoolSize;
import static web.app.moldunity.util.ThreadPoolAndHikariPoolSize.getPoolSize;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    @Bean
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(getPoolSize());
        executor.setMaxPoolSize(getMaxPoolSize());
        executor.setQueueCapacity(512);
        executor.setThreadNamePrefix("async_");
        executor.initialize();

        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }
}














