package com.github.ephelsa.credijustotest.repository.user

import com.github.ephelsa.credijustotest.datasource.local.LocalUserDatasource
import com.github.ephelsa.credijustotest.datasource.remote.json.UserJSON
import com.github.ephelsa.credijustotest.datasource.remote.user.RemoteUserDatasource
import com.github.ephelsa.credijustotest.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * _Real_ implementation of [UserRepository].
 */
class UserRepositoryImpl(
    override val dispatcher: CoroutineDispatcher,
    private val remoteUserDatasource: RemoteUserDatasource,
    private val localUserDatasource: LocalUserDatasource,
) : UserRepository {

    /**
     * Try to find an user locally. In case the user hasn't been found, fetch the total of users from the
     * remote datasource to update the cache.
     *
     * If the user hasn't been found after refreshing the cache, this mean that the user doesn't exist.
     */
    override suspend fun findUserById(userId: Int): Result<User> {
        return try {
            val user = coroutineScope {
                async(dispatcher) { localUserDatasource.findUserById(userId) }
            }.await()

            if (user == null) {
                val refreshedUser = coroutineScope {
                    async(dispatcher) {
                        val response = remoteUserDatasource.fetchUsers()

                        localUserDatasource.cacheUsers(response.map(UserJSON::toDomain))
                        localUserDatasource.findUserById(userId)
                    }
                }.await()

                if (refreshedUser != null)
                    Result.success(refreshedUser)
                else
                    Result.failure(NullPointerException())

            } else {
                Result.success(user)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}