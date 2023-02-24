package com.cache.projectcache.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class CacheMessage implements Serializable {

    private String key;
    private String value;
}
