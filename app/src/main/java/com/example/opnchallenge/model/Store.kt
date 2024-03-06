package com.example.opnchallenge.model

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val name: String,
    val rating: Double,
    val openingTime: String,
    val closingTime: String,
)