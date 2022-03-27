package com.github.ephelsa.credijustotest.repository.user

import com.github.ephelsa.credijustotest.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Fake implementation of [UserRepository] useful for tests or development environment.
 *
 * @param dispatcher see [UserRepository] for more information.
 */
class FakeUserRepository(
    override val dispatcher: CoroutineDispatcher,
) : UserRepository {
    override suspend fun findUserById(userId: Int): Result<User> = withContext(dispatcher) {
        val user = User(
            id = userId,
            name = "John Doe",
            username = "john.doe"
        )

        delay(1_000)
        Result.success(user)
    }
}