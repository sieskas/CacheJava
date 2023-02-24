package com.cache.projectcache.cache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import org.ehcache.jsr107.EhcacheCachingProvider;

public class CacheSingleton {

    private static final String CACHE_NAME = "myCache";

    private static CacheManager cacheManager;

    public static Cache getCache() {
        if (cacheManager == null) {
            CachingProvider cachingProvider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
            cacheManager = cachingProvider.getCacheManager();
            Configuration<Object, Object> config = new MutableConfiguration<>()
                    .setTypes(Object.class, Object.class);
            cacheManager.createCache(CACHE_NAME, config);
        }
        return cacheManager.getCache(CACHE_NAME);
    }

    public static void shutdownCacheManager() {
        if (cacheManager != null) {
            cacheManager.close();
            cacheManager = null;
        }
    }

}
