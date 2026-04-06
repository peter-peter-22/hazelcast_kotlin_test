package com.example.hazelcast_kotlin_test

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.map.IMap

data class Customer(val firstName: String, val lastName: String)

fun main() {
    // Create an embedded member
    val clientConfig = Config()
    clientConfig.clusterName = "dev"
    val client = Hazelcast.newHazelcastInstance(clientConfig)

    // Create a map
    val mapCustomers: IMap<String?, Customer?> = client.getMap("customers") //creates the map proxy

    // Write data
    mapCustomers.put("1", Customer("Joe", "Smith"))
    println("inserted customer with id 1")

    // Read data
    println("reading customer from id 1: ${mapCustomers["1"] ?: "customer not found"}")
}