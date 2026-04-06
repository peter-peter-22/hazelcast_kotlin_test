package com.example.hazelcast_kotlin_test

import com.hazelcast.core.HazelcastInstance
import org.springframework.stereotype.Service

@Service
class ReadThrough(private val hazelcast: HazelcastInstance) {
    fun getBook(id: Int): Book? {
        return hazelcast.getMap<Int,Book>("books_read_through")[id]
    }
}