package com.example.webflux.appCache.primary;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class EhCacheConfiguration {

    public static final String EH_CACHE = "ehCache";

    @Bean(name = EH_CACHE)
    public CacheManager ehCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                new ConcurrentMapCache("numCache"),
                new ConcurrentMapCache("numCache2")
        ));
        return cacheManager;
    }
}
