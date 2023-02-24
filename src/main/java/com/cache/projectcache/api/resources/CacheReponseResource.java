package com.cache.projectcache.api.resources;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CacheReponseResource {

    private List<CacheResource> caches;

}
