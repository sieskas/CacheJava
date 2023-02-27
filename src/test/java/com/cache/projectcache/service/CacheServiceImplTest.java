package com.cache.projectcache.service;

import com.cache.projectcache.domain.exception.NotFoundCacheException;
import com.cache.projectcache.domain.model.Cache;
import com.cache.projectcache.outcall.multicast.MulticastPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CacheServiceImplTest {

    @Mock
    private MulticastPublisher multicastPublisher;
    private CacheService cacheService;

    @BeforeEach
    void init() {
        cacheService = new CacheServiceImpl(multicastPublisher);
    }

    @Test
    void testCacheUpdate() throws IOException, NotFoundCacheException {
        String key = "q";
        String value = "w";
        cacheService.updateCache(Cache.builder().key(key).value(value).build());
        Cache cacheReturn = cacheService.getCache(key);
        assertEquals(key, cacheReturn.getKey());
        assertEquals(value, cacheReturn.getValue());
    }

}