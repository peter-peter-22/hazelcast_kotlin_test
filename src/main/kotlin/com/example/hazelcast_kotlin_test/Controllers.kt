package com.example.hazelcast_kotlin_test

import com.hazelcast.core.HazelcastInstance
import org.springframework.web.bind.annotation.*

@RestController
class Controllers(
    private val cacheAside: CacheAside,
    private val cacheAsideNear: CacheAsideNear,
    private val hazelcastInstance: HazelcastInstance,
) {
    private var myCache: CacheAside = cacheAsideNear

    @GetMapping("/read/{id}")
    fun read(@PathVariable id: Int): Book? {
        println("Controller: reading book with id: $id")
        return myCache.getBook(id)
    }

    @PostMapping("/write")
    fun write(@RequestBody book: Book): Book? {
        println("Controller: writing book with id: ${book.id}")
        return myCache.setBook(book.id, book)
    }

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Int) {
        println("Controller: deleting book with id: $id")
        myCache.deleteBook(id)
    }

    @PostMapping("/clear")
    fun clear() {
        println("Controller: clearing all books")
        myCache.clearBooks()
    }

    @PostMapping("/set-near-cache/{enabled}")
    fun setNearCache(@PathVariable enabled: Boolean) {
        println("Controller: setting near cache to $enabled")
        myCache = if (enabled) {
            cacheAsideNear
        } else {
            cacheAside
        }
    }

    @GetMapping("/stats")
    fun getStats(): Map<String, Any> {
        val map = hazelcastInstance.getMap<Int, Book>("books_near")
        map[1]
        map[1]
        map[1]
        map[1]

        val nearCacheStats = map.localMapStats.nearCacheStats
        return mapOf(
            "hits" to nearCacheStats.hits,
            "misses" to nearCacheStats.misses,
            "evictions" to nearCacheStats.evictions,
            "expirations" to nearCacheStats.expirations,
            "ownedEntryCount" to nearCacheStats.ownedEntryCount,
            "distributedCacheEntries" to map.entries.size
        )
    }
}