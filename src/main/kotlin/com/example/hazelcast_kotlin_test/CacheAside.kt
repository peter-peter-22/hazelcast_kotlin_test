package com.example.hazelcast_kotlin_test

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CacheAside(
    private val bookService: BookService,
) {
    @Cacheable("books")
    fun getBook(id: Int): Book? {
        return bookService.getBook(id)
    }

    @CachePut(value = ["books"], key = "#id")
    fun setBook(id: Int, book: Book): Book {
        bookService.setBook(id, book)
        return book
    }

    @CacheEvict(value = ["books"], key = "#id")
     fun deleteBook(id: Int) {
        bookService.deleteBook(id)
    }

    @CacheEvict(value = ["books"], allEntries = true)
     fun clearBooks() {
        bookService.clearBooks()
    }
}