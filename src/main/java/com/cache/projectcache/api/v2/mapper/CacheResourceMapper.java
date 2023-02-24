package com.cache.projectcache.api.v2.mapper;

import com.cache.projectcache.api.resources.CacheRequestResource;
import com.cache.projectcache.api.resources.CacheResource;
import com.cache.projectcache.domain.model.Cache;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CacheResourceMapper {

    Cache toModel(CacheRequestResource cacheRequestResource);

    CacheResource toResource(Cache cache);

    //@Mapping(target = "caches", source = "cacheList") // qualifiedByName = "convertItemType")
    List<CacheResource> toResources(List<Cache> cacheList);

//    @Named("list")
//    default CacheReponseResource convertList(List<Cache> cacheList) {
//        List<CacheResource> resources = new ArrayList<>();
//        for (Cache cache : cacheList) {
//            resources.add(CacheResource)
//        }
//        return TypeEnum.valueOf(type);
//    }

}