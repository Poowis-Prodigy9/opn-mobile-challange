package com.example.opnchallenge.data

import android.util.Log
import com.example.opnchallenge.model.Product
import com.example.opnchallenge.model.Store
import kotlinx.coroutines.delay
import javax.inject.Inject

// This mock is here to substitute broken API
class MockStoreRepository @Inject constructor() : StoreRepository {
    override suspend fun getStore(): Store = Store(
        name = "The Coffee Shop",
        rating = 4.5,
        openingTime = "15:01:01.772Z",
        closingTime = "19:45:51.365Z"
    )

    override suspend fun getProducts(): List<Product> = listOf(
        Product(
            name = "Latte",
            price = 50.0,
            imageUrl = "https://www.nespresso.com/ncp/res/uploads/recipes/nespresso-recipes-Latte-Art-Tulip.jpg"
        ),
        Product(
            name = "Dark Tiramisu Mocha",
            price = 75.0,
            imageUrl = "https://www.nespresso.com/shared_res/mos/free_html/sg/b2b/b2ccoffeerecipes/listing-image/image/dark-tiramisu-mocha.jpg"
        )
    )

    override suspend fun createOder(products: List<Product>, address: String) {
        Log.i("MockStoreRepository", "address: ${address}\nproducts:${products}")
        
        delay(2000L)
        return
    }
}