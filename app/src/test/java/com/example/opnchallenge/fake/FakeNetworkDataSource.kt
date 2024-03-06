package com.example.opnchallenge.fake

import com.example.opnchallenge.data.network.NetworkDataSource
import com.example.opnchallenge.model.CreateOrderRequest
import com.example.opnchallenge.model.Product
import com.example.opnchallenge.model.Store
import kotlinx.coroutines.delay

class FakeNetworkDataSource : NetworkDataSource {
    override suspend fun getStore(): Store = Store(
        name = "The Fake Coffee Shop",
        rating = 4.9,
        openingTime = "08:00:00.000Z",
        closingTime = "18:00:00.000Z"
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
        ),
        Product(
            name = "Americano",
            price = 65.0,
            imageUrl = "https://www.nespresso.com/shared_res/mos/free_html/sg/b2b/b2ccoffeerecipes/listing-image/image/dark-tiramisu-mocha.jpg"
        ),
        Product(
            name = "Matcha Latte",
            price = 80.0,
            imageUrl = "https://www.nespresso.com/shared_res/mos/free_html/sg/b2b/b2ccoffeerecipes/listing-image/image/dark-tiramisu-mocha.jpg"
        )
    )

    override suspend fun createOrder(body: CreateOrderRequest) {
        delay(2000L)
        return
    }
}