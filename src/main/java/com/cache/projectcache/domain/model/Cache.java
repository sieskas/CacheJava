package com.cache.projectcache.domain.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cache implements Serializable {

    public String key;
    public String value;
}
