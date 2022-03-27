package com.github.ephelsa.credijustotest.di

import com.github.ephelsa.credijustotest.datasource.local.LocalUserDatasource
import com.github.ephelsa.credijustotest.datasource.remote.post.RemotePostDatasource
import com.github.ephelsa.credijustotest.datasource.remote.user.RemoteUserDatasource
import com.github.ephelsa.credijustotest.repository.post.PostRepository
import com.github.ephelsa.credijustotest.repository.post.PostRepositoryImpl
import com.github.ephelsa.credijustotest.repository.user.UserRepository
import com.github.ephelsa.credijustotest.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    internal fun providePostRepository(
        remotePostDatasource: RemotePostDatasource,
        userRepository: UserRepository,
    ): PostRepository {
        return PostRepositoryImpl(
            dispatcher = Dispatchers.IO,
            remotePostDatasource = remotePostDatasource,
            userRepository = userRepository,
        )
    }

    @Provides
    internal fun provideUserRepository(
        remoteUserDatasource: RemoteUserDatasource,
        localUserDatasource: LocalUserDatasource,
    ): UserRepository {
        return UserRepositoryImpl(
            dispatcher = Dispatchers.IO,
            remoteUserDatasource = remoteUserDatasource,
            localUserDatasource = localUserDatasource,
        )
    }
}