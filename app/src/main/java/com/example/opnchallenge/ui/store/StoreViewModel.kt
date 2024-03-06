package com.example.opnchallenge.ui.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opnchallenge.data.StoreRepository
import com.example.opnchallenge.model.CartProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StoreViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(StoreUiState())
    val uiState: StateFlow<StoreUiState> = _uiState.asStateFlow()

    init {
        if (_uiState.value.store == null || _uiState.value.products == null) {
            viewModelScope.launch {
                try {
                    _uiState.value = StoreUiState(isLoading = true)
                    val store = async { storeRepository.getStore() }
                    val products = async { storeRepository.getProducts() }
                    _uiState.value = StoreUiState(
                        isLoading = false,
                        store = store.await(),
                        products = products.await().map { product ->
                            CartProduct(
                                product = product
                            )
                        }
                    )
                } catch (e: Exception) {
                    _uiState.value = StoreUiState(
                        isLoading = false,
                        errorMessage = "Cannot get store information"
                    )
                }

            }
        }
    }

    fun updateProductCount(productIndex: Int, newCount: Int) {
        if (newCount >= 0) {
            _uiState.update {
                it.copy(
                    products = it.products?.let { list ->
                        val newProducts = list.toMutableList()
                        newProducts[productIndex] = newProducts[productIndex].copy(
                            count = newCount
                        )
                        newProducts
                    }
                )
            }
        }
    }

    fun updateAddress(address: String) {
        _uiState.update { it.copy(address = address) }
    }


    fun createOrder(onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            _uiState.value.products?.let { products ->

                _uiState.update {
                    it.copy(
                        isOrderLoading = true
                    )
                }

                storeRepository.createOder(products.filter { it.count > 0 }
                    .map { it.product }, _uiState.value.address)

                _uiState.update {
                    it.copy(
                        isOrderLoading = false,
                        address = "",
                        products = it.products?.map { product ->
                            product.copy(
                                count = 0
                            )
                        }
                    )
                }
            }

            onComplete()
        }
    }
}