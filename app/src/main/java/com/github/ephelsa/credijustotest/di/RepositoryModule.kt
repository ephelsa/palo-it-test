package com.github.ephelsa.credijustotest.di

import com.github.ephelsa.credijustotest.repository.post.FakePostRepository
import com.github.ephelsa.credijustotest.repository.post.PostRepository
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
    internal fun providePostRepository(): PostRepository {
        return FakePostRepository(dispatcher = Dispatchers.IO, numberOfData = 10)
    }
}