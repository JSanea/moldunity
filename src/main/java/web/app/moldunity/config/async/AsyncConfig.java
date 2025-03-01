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

        /** Set the core pool size */
        executor.setCorePoolSize(getPoolSize());

        /** Set the maximum pool size */
        executor.setMaxPoolSize(getMaxPoolSize());

        /** Set the capacity of the task queue */
        executor.setQueueCapacity(QUEUE_CAPACITY);

        /** Set the thread name prefix */
        executor.setThreadNamePrefix("async_");

        executor.initialize();

        return executor;
    }
}














