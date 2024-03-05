package com.example.opnchallenge.data.network


import com.example.opnchallenge.model.Product
import com.example.opnchallenge.model.Store
import retrofit2.http.GET


interface NetworkDataSource {
    @GET("store")
    suspend fun getStore(): Store

    @GET("products")
    suspend fun getProducts(): List<Product>
}
