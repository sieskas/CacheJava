package com.cache.projectcache.api.v1.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CacheRequestResource {

    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;

}
