package com.example.hazelcast_kotlin_test

import com.hazelcast.map.MapLoader
import org.springframework.stereotype.Component

@Component
class BookMapLoader(private val bookService: BookService) : MapLoader<Int, Book> {

    override fun load(key: Int): Book? {
        println("Cache miss - loading from database: $key")
        return bookService.getBook(key)
    }

    override fun loadAll(keys: MutableCollection<Int>): Map<Int, Book> {
        return keys.mapNotNull { key ->
            bookService.getBook(key)?.let { key to it }
        }.toMap()
    }

    override fun loadAllKeys(): Iterable<Int>? = null // Return null to disable preload
}