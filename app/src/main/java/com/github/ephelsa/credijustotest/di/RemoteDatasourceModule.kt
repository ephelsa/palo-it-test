package com.github.ephelsa.credijustotest.di

import com.github.ephelsa.credijustotest.datasource.remote.KtorConfig
import com.github.ephelsa.credijustotest.datasource.remote.post.RemotePostDatasource
import com.github.ephelsa.credijustotest.datasource.remote.post.RemotePostDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object RemoteDatasourceModule {

    @Provides
    internal fun provideKtorClient(): HttpClient = KtorConfig.client()

    @Provides
    internal fun provideRemotePostDatasource(
        client: HttpClient,
    ): RemotePostDatasource = RemotePostDatasourceImpl(
        dispatcher = Dispatchers.IO,
        client = client,
    )
}