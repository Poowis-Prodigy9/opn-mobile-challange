package com.example.opnchallenge.data.network


import com.example.opnchallenge.model.CreateOrderRequest
import com.example.opnchallenge.model.Product
import com.example.opnchallenge.model.Store
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface NetworkDataSource {
    @GET("store")
    suspend fun getStore(): Store

    @GET("products")
    suspend fun getProducts(): List<Product>

    @Headers("Content-Type: application/json")
    @POST("order")
    suspend fun createOrder(@Body body: CreateOrderRequest)
}
