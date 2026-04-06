package com.example.hazelcast_kotlin_test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching


@SpringBootApplication
@EnableCaching
class HazelcastKotlinTestApplication

fun main(args: Array<String>) {
    runApplication<HazelcastKotlinTestApplication>(*args)
}