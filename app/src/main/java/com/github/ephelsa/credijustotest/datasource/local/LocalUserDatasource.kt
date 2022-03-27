package com.github.ephelsa.credijustotest.datasource.local

import com.github.ephelsa.credijustotest.domain.User
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Local datasource for users.
 *
 * For a _real_ based implementation, see [LocalUserDatasourceInMemory].
 *
 * @see LocalUserDatasourceInMemory
 */
interface LocalUserDatasource {
    /**
     * [CoroutineDispatcher] to confine the coroutine executions to a specific thread.
     *
     * Useful for unit tests too.
     */
    val dispatcher: CoroutineDispatcher

    /**
     * Store locally a bunch of [users][User].
     *
     * @param users list to be cached.
     */
    suspend fun cacheUsers(users: List<User>)

    /**
     * Find a user previously stored by an id.
     *
     * @param userId user's id to find.
     */
    suspend fun findUserById(userId: Int): User?
}