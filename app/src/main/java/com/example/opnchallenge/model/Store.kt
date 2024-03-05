package com.example.opnchallenge.model

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val name: String,
    val rating: Float,
    val openingTime: String,
    val closingTime: String,
)