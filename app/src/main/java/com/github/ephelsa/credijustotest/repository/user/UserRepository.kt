package com.github.ephelsa.credijustotest.repository.user

import com.github.ephelsa.credijustotest.domain.User
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Repository to manipulate user stuff.
 *
 * For a _real_ implementation, see [UserRepositoryImpl].
 *
 * @see UserRepositoryImpl
 */
interface UserRepository {
    /**
     * [CoroutineDispatcher] to confine the coroutine executions to a specific thread.
     *
     * Useful for unit tests too.
     */
    val dispatcher: CoroutineDispatcher

    /**
     * Find an user by its id.
     *
     * @param userId user's id to be found.
     */
    suspend fun findUserById(userId: Int): Result<User>
}