package com.example.opnchallenge.data.network

import com.example.opnchallenge.data.StoreRepository
import com.example.opnchallenge.model.CreateOrderRequest
import com.example.opnchallenge.model.Product
import com.example.opnchallenge.model.Store
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkStoreRepository @Inject constructor(
    private val appService: NetworkDataSource,
) : StoreRepository {

    override suspend fun getStore(): Store = appService.getStore()

    override suspend fun getProducts(): List<Product> = appService.getProducts()

    override suspend fun createOder(products: List<Product>, address: String) =
        appService.createOrder(
            body = CreateOrderRequest(
                products = products, address = address
            )
        )
}
