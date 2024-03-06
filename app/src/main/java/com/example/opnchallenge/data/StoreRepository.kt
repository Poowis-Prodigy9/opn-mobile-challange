package com.example.opnchallenge.data

import com.example.opnchallenge.model.Product
import com.example.opnchallenge.model.Store

interface StoreRepository {
    suspend fun getStore(): Store
    suspend fun getProducts(): List<Product>
    suspend fun createOder(products: List<Product>, address: String)
}
