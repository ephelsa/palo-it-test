package com.github.ephelsa.credijustotest.di

import com.github.ephelsa.credijustotest.datasource.remote.KtorConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object RemoteDatasourceModule {

    @Provides
    internal fun provideKtorClient(): HttpClient = KtorConfig.client()

}