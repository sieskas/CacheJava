package com.cache.projectcache.service;

import com.cache.projectcache.domain.exception.NotFoundCacheException;
import com.cache.projectcache.domain.model.Cache;

import java.io.IOException;
import java.util.List;

public interface CacheService {

    Cache getCache(String key) throws NotFoundCacheException;

    List<Cache> getAllCache();

    void updateCache(Cache cache) throws IOException;

}
