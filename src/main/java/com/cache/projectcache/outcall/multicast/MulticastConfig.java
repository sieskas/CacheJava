package com.cache.projectcache.outcall.multicast;

import com.cache.projectcache.outcall.multicast.listener.MulticastCacheListenerImpl;
import com.cache.projectcache.service.CacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MulticastConfig {
    @Value("${multicast.group}")
    private String multicastGroup;

    @Value("${multicast.port}")
    private int multicastPort;
    @Bean
    public MulticastCacheListenerImpl multicastListener(CacheService cacheService) throws IOException {
        MulticastCacheListenerImpl listener =
                new MulticastCacheListenerImpl(cacheService, multicastGroup, multicastPort);
        listener.start();
        return listener;
    }
}
