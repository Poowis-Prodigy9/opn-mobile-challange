package com.example.opnchallenge.data.network

import com.example.opnchallenge.model.Product
import com.example.opnchallenge.model.Store
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultStoreApiService @Inject constructor() : NetworkDataSource {
    private val baseUrl = "https://c8d92d0a-6233-4ef7-a229-5a91deb91ea1.mock.pstmn.io/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val service: NetworkDataSource by lazy {
        retrofit.create(NetworkDataSource::class.java)
    }

    override suspend fun getStore(): Store = service.getStore()
    override suspend fun getProducts(): List<Product> = service.getProducts()
}