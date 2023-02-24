package com.cache.projectcache.api.v2.controller;

import com.cache.projectcache.api.shared.ApiPaths;
import com.cache.projectcache.api.resources.CacheReponseResource;
import com.cache.projectcache.api.resources.CacheRequestResource;
import com.cache.projectcache.api.resources.CacheResource;
import com.cache.projectcache.api.v2.mapper.CacheResourceMapper;
import com.cache.projectcache.domain.exception.NotFoundCacheException;
import com.cache.projectcache.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ApiPaths.API_BASE_PATH)
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;
    private final CacheResourceMapper mapper;

    @PostMapping(
            value = ApiPaths.API_END_POINT_UPDATE,
            produces = {APPLICATION_JSON_VALUE},
            consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> doUpdateCache(@RequestBody CacheRequestResource cacheRequest) throws IOException {
        if (cacheRequest == null
                || StringUtils.isEmpty(cacheRequest.getKey())
                || StringUtils.isEmpty(cacheRequest.getValue())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        cacheService.updateCache(mapper.toModel(cacheRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/cache/{key}")
    public ResponseEntity<CacheResource> getCache(@PathVariable String key) {
        if (StringUtils.isEmpty(key)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(mapper.toResource(
                    cacheService.getCache(key)), HttpStatus.OK);
        } catch (NotFoundCacheException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //todo interceptor
        }
    }

    @GetMapping(ApiPaths.API_END_POINT_GET)
    public ResponseEntity<List<CacheResource>> getAllCache() {

        return new ResponseEntity<>(mapper.toResources(
                cacheService.getAllCache()), HttpStatus.OK);
    }
}
