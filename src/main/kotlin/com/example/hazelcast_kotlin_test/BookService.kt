package com.example.hazelcast_kotlin_test

import org.springframework.stereotype.Service

@Service
class BookService {
    private val books: MutableMap<Int, Book> = mutableMapOf(
        1 to Book("Kotlin in Action", 1, "John"),
        2 to Book("Kotlin for Java Developers", 2, "Jacob")
    )

    fun getBook(id: Int): Book? {
        println("BookService: getting book with id: $id")
        return books[id]
    }

    fun setBook(id: Int, book: Book) {
        println("BookService: setting book with id: $id")
        books[id] = book
    }

    fun deleteBook(id: Int) {
        println("BookService: deleting book with id: $id")
        books.remove(id)
    }

    fun clearBooks() {
        println("BookService: clearing all books")
        books.clear()
    }
}