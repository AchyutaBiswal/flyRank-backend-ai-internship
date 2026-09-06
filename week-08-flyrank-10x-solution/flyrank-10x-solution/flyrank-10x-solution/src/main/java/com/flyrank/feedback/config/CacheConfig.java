package com.flyrank.feedback.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String FEEDBACK_ANALYSIS_CACHE = "feedbackAnalysis";
    public static final String REPORT_STATS_CACHE = "reportStats";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(FEEDBACK_ANALYSIS_CACHE, REPORT_STATS_CACHE);
    }
}
