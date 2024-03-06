package com.example.opnchallenge.network

import com.example.opnchallenge.data.network.NetworkStoreRepository
import com.example.opnchallenge.fake.FakeNetworkDataSource
import com.example.opnchallenge.rules.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NetworkStoreRepositoryTest {

    private lateinit var networkStoreRepository: NetworkStoreRepository

    private lateinit var networkDataSource: FakeNetworkDataSource


    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setupViewModel() {
        networkDataSource = FakeNetworkDataSource()

        networkStoreRepository = NetworkStoreRepository(networkDataSource)
    }

    @Test
    fun getStore() = runTest {
        // assert store
        Assert.assertEquals(networkDataSource.getStore(), networkStoreRepository.getStore())
    }


    @Test
    fun getProducts() = runTest {
        // assert store
        Assert.assertEquals(networkDataSource.getProducts(), networkStoreRepository.getProducts())
    }
}