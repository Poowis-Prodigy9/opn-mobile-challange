package com.example.opnchallenge.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequest(
    val products: List<Product>,
    @SerialName(value = "delivery_address")
    val address: String,
)