package com.example.opnchallenge.ui.store

import com.example.opnchallenge.model.CartProduct
import com.example.opnchallenge.model.Store

data class StoreUiState(
    val store: Store? = null,
    val products: List<CartProduct>? = null,
    val isLoading: Boolean = false,

    val address: String = "",
    val isOrderLoading: Boolean = false,

    val errorMessage: String? = null,
)
