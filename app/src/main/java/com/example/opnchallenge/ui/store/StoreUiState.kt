package com.example.opnchallenge.ui.store

import com.example.opnchallenge.model.Product
import com.example.opnchallenge.model.Store

data class StoreUiState(
    val store: Store? = null,
    val products: List<Product> = emptyList()
)
