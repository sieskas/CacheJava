package com.cache.projectcache.api.v1.controller;

import com.cache.projectcache.api.v1.Model.CacheRequestResource;
import com.cache.projectcache.service.ICacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;

import static com.cache.projectcache.api.v1.shared.ApiPaths.API_BASE_PATH;
import static com.cache.projectcache.api.v1.shared.ApiPaths.API_END_POINT_UPDATE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(API_BASE_PATH)
@RequiredArgsConstructor
public class CacheController {

    private final ICacheService cacheService;

    @PostMapping(
            value = API_END_POINT_UPDATE,
            produces = {APPLICATION_JSON_VALUE},
            consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> doCreateInvoice(@RequestBody CacheRequestResource cacheRequest) {
        cacheService.updateCacheWithPushMultiCast(cacheRequest.getKey(), cacheRequest.getValue());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
