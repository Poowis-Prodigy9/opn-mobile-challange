package com.example.opnchallenge.di

import com.example.opnchallenge.data.StoreRepository
import com.example.opnchallenge.data.network.DefaultStoreApiService
import com.example.opnchallenge.data.network.NetworkDataSource
import com.example.opnchallenge.data.network.NetworkStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTaskRepository(repository: NetworkStoreRepository): StoreRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: DefaultStoreApiService): NetworkDataSource
}