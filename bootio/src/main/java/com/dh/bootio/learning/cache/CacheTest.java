package com.dh.bootio.learning.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheTest {

    public static void main(String[] args) {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        cache.stats().toString();
    }

}
