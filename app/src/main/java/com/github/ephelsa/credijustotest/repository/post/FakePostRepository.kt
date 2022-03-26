package com.github.ephelsa.credijustotest.repository.post

import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Fake implementation of [PostRepository] useful for tests or to manipulate [Post]
 * data in a controlled environment.
 *
 * @property dispatcher used to confine the coroutine executions to a specific thread.
 * @property numberOfData when a method returns a list of something, this describes how many
 * items are required.
 */
class FakePostRepository(
    private val dispatcher: CoroutineDispatcher,
    private val numberOfData: Int = 5,
) : PostRepository {

    override suspend fun fetchPosts(): Result<List<Post>> = withContext(dispatcher) {
        delay(1_000)
        Result.success((1..numberOfData).map(::createFakeData))
    }

    private fun createFakeData(id: Int): Post {
        return  Post(
            id = id,
            user = User(
                id = id,
                name = "User $id",
                username = "user.$id",
            ),
            title = "Post $id",
            body = "Post Body $id",
        )
    }
}