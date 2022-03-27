package com.github.ephelsa.credijustotest.di

import com.github.ephelsa.credijustotest.datasource.remote.post.RemotePostDatasource
import com.github.ephelsa.credijustotest.repository.post.PostRepository
import com.github.ephelsa.credijustotest.repository.post.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    internal fun providePostRepository(
        remotePostDatasource: RemotePostDatasource,
    ): PostRepository {
        return PostRepositoryImpl(
            dispatcher = Dispatchers.IO,
            remotePostDatasource = remotePostDatasource,
        )
    }
}