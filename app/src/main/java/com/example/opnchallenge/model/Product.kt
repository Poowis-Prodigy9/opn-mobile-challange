package com.example.opnchallenge.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val name: String,
    val price: Double,
    val imageUrl: String,
)