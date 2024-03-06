package com.example.opnchallenge.store

import com.example.opnchallenge.fake.FakeStoreRepository
import com.example.opnchallenge.model.CartProduct
import com.example.opnchallenge.rules.TestDispatcherRule
import com.example.opnchallenge.ui.store.StoreViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StoreViewModelTest {

    private lateinit var storeViewModel: StoreViewModel

    private lateinit var storeRepository: FakeStoreRepository


    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setupViewModel() {
        storeRepository = FakeStoreRepository()

        storeViewModel = StoreViewModel(storeRepository)
    }

    @Test
    fun loadStoreAndProduct() = runTest {
        // assert store
        assertEquals(storeRepository.getStore(), storeViewModel.uiState.value.store)

        // assert products
        assertEquals(
            storeRepository.getProducts().map { CartProduct(product = it, count = 0) },
            storeViewModel.uiState.value.products
        )
    }

    @Test
    fun updateProductCount() = runTest {
        storeViewModel.updateProductCount(1, 3)
        storeViewModel.updateProductCount(3, 123)


        assertEquals(3, storeViewModel.uiState.value.products!![1].count)
        assertEquals(123, storeViewModel.uiState.value.products!![3].count)
    }

    @Test
    fun updateAddress() = runTest {
        val fakeAddress = "This is a random address"
        storeViewModel.updateAddress(fakeAddress)

        assertEquals(fakeAddress, storeViewModel.uiState.value.address)
    }

    @Test
    fun createOrderAndResetOrder() = runTest {
        val fakeAddress = "This is a random address"
        storeViewModel.createOrder()

        assertEquals("", storeViewModel.uiState.value.address)

        // assert products
        assertEquals(
            storeRepository.getProducts().map { CartProduct(product = it, count = 0) },
            storeViewModel.uiState.value.products
        )
    }
}