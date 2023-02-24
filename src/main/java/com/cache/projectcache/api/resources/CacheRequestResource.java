package com.cache.projectcache.api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CacheRequestResource {

    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;

}
