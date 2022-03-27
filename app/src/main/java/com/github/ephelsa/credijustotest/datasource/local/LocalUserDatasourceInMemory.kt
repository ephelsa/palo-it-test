package com.github.ephelsa.credijustotest.datasource.local

import com.github.ephelsa.credijustotest.domain.User
import kotlinx.coroutines.CoroutineDispatcher

/**
 * This implementation of [LocalUserDatasource] store all the data in memory.
 *
 * The [users] set store all the users provided by [cacheUsers].
 *
 * This implementation doesn't require [dispatcher].
 */
class LocalUserDatasourceInMemory : LocalUserDatasource {

    override val dispatcher: CoroutineDispatcher
        get() = throw NullPointerException("Dispatcher not required in the current implementation")

    private val users = mutableSetOf<User>()

    override suspend fun cacheUsers(users: List<User>) {
        restoreCache()
        this.users.addAll(users)
    }

    private fun restoreCache() {
        users.clear()
    }

    override suspend fun findUserById(userId: Int): User? {
        return users.find { it.id == userId }
    }
}