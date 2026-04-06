package com.example.hazelcast_kotlin_test

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.spring.cache.HazelcastCacheManager
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import tools.jackson.module.kotlin.jacksonObjectMapper

@Configuration
class CacheConfig {
    @Bean
    @Primary
    fun myHazelcastInstance(): HazelcastInstance {
        val config = Config.load() // Loads from hazelcast.yaml in the classpath
        println("Cache config:")
        val mapper = jacksonObjectMapper()
        for (e in config.mapConfigs.entries) {
            println("Map: " + e.key)
            println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(e.value))
        }
        return Hazelcast.getOrCreateHazelcastInstance(config)
    }

    @Bean
    fun cacheManager(hazelcastInstance: HazelcastInstance): CacheManager {
        return HazelcastCacheManager(hazelcastInstance)
    }
}