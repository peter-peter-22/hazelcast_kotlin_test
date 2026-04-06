package com.example.hazelcast_kotlin_test

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CacheAsideNear(
    private val bookService: BookService,
): CacheAside(bookService) {
    @Cacheable("books_near")
    override fun getBook(id: Int): Book? {
        return bookService.getBook(id)
    }

    @CachePut(value = ["books_near"], key = "#id")
    override fun setBook(id: Int, book: Book): Book {
        bookService.setBook(id, book)
        return book
    }

    @CacheEvict(value = ["books_near"], key = "#id")
    override fun deleteBook(id: Int) {
        bookService.deleteBook(id)
    }

    @CacheEvict(value = ["books_near"], allEntries = true)
    override fun clearBooks() {
        bookService.clearBooks()
    }
}