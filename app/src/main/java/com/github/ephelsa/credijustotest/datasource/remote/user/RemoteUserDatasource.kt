package com.github.ephelsa.credijustotest.datasource.remote.user

import com.github.ephelsa.credijustotest.datasource.remote.json.UserJSON
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Remote datasource for users.
 *
 * For a _real_ based implementation, see [RemoteUserDatasourceImpl].
 *
 * @see RemoteUserDatasourceImpl
 */
interface RemoteUserDatasource {
    /**
     * [CoroutineDispatcher] to confine the coroutine executions to a specific thread.
     *
     * Useful for unit tests too.
     */
    val dispatcher: CoroutineDispatcher

    /**
     * Fetch a list of [UserJSON]
     */
    suspend fun fetchUsers(): List<UserJSON>
}