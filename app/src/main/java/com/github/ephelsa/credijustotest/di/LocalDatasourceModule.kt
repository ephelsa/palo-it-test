package com.github.ephelsa.credijustotest.di

import com.github.ephelsa.credijustotest.datasource.local.LocalUserDatasource
import com.github.ephelsa.credijustotest.datasource.local.LocalUserDatasourceInMemory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatasourceModule {

    @Provides
    @Singleton
    internal fun provideLocalUserDatasource(): LocalUserDatasource = LocalUserDatasourceInMemory()
}