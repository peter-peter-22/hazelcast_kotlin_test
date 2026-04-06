package com.example.hazelcast_kotlin_test

import java.io.Serializable

data class Book(val title: String, val id: Int, val author: String) : Serializable
