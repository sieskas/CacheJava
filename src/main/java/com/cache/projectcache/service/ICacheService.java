package com.cache.projectcache.service;

import java.util.List;

public interface ICacheService {

    String getCache(String key);

    List<String> getAllCache();

    void deleteCache(String key);

    void updateCacheWithPushMultiCast(String key, String value);

    void updateCache(String key, String value);


}
