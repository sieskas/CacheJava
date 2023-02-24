package com.cache.projectcache.config;

import com.cache.projectcache.listener.CacheUpdateListener;
import com.cache.projectcache.service.ICacheService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheUpdateListener cacheUpdateListener(ICacheService cacheService) throws Exception {
        return new CacheUpdateListener(cacheService);
    }
}
