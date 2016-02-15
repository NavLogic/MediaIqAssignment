package com.mediaiq.aggregator.concurrent;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@Configuration
@EnableAsync
public class UnifiedResultParallelExecutorFactory {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UnifiedResultParallelExecutor getExecutor() {
        return new UnifiedResultParallelExecutor();
    }
}
