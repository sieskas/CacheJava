package com.cache.projectcache.service;

import com.cache.projectcache.domain.exception.NotFoundCacheException;
import com.cache.projectcache.domain.model.CacheSingleton;
import com.cache.projectcache.outcall.multicast.MulticastPublisher;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {
    private final Cache<String, String> cache;
    private final MulticastPublisher multicastPublisher;

    public CacheServiceImpl(MulticastPublisher multicastPublisher) {
        this.multicastPublisher = multicastPublisher;
        cache = CacheSingleton.getCache();
    }

    @Override
    public void updateCache(com.cache.projectcache.domain.model.Cache objCache) throws IOException {
        if (objCache == null || objCache.getKey() == null) {
            return;
        }

        if (!cache.containsKey(objCache.getKey())
                || !cache.get(objCache.getKey()).equals(objCache.getValue())) {
            System.out.println("Received object: " + objCache);
            cache.put(objCache.getKey(), objCache.getValue());
            multicastPublisher.sendPacket(objCache);
        }
    }

    @Override
    public com.cache.projectcache.domain.model.Cache getCache(String key) throws NotFoundCacheException {
        if (key == null) {
            return null;
        }
        String value = cache.get(key);
        if (value == null) {
            throw new NotFoundCacheException("");
        }

        return com.cache.projectcache.domain.model.Cache.builder()
                .key(key)
                .value(value)
                .build();
    }

    @Override
    public List<com.cache.projectcache.domain.model.Cache> getAllCache() {
        List<com.cache.projectcache.domain.model.Cache> list = new ArrayList<>();
        for(Cache.Entry<String, String> entry : cache) {
            list.add(com.cache.projectcache.domain.model.Cache.builder()
                    .key(entry.getKey())
                    .value(entry.getValue())
                    .build());
        }
        return list;
    }
}
