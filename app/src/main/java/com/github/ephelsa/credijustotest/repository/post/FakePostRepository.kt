package com.github.ephelsa.credijustotest.repository.post

import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Fake implementation of [PostRepository] useful for tests or to manipulate [Post]
 * data in a controlled environment.
 *
 * @param dispatcher see [PostRepository] for more information.
 * @param numberOfData when a method returns a list of something, this describes how many
 * items are required.
 */
class FakePostRepository(
    override val dispatcher: CoroutineDispatcher,
    private val numberOfData: Int = 5,
) : PostRepository {

    override suspend fun fetchPosts(): Result<List<Post>> = withContext(dispatcher) {
        delay(1_000)
        Result.success((1..numberOfData).map(::createPost))
    }

    override suspend fun fetchCommentsByPost(postId: Int): Result<List<Comment>> = withContext(dispatcher) {
        delay(1_000)
        val result = (1..numberOfData).map { id ->
            createComment(postId, id)
        }
        Result.success(result)
    }

    private fun createPost(id: Int): Post {
        return Post(
            id = id,
            user = User(
                id = id,
                name = "User #$id",
                username = "user.$id",
            ),
            title = "Post #$id",
            body = "Post Body $id",
        )
    }

    private fun createComment(postId: Int, id: Int): Comment {
        return Comment(
            postId = postId,
            id = id,
            email = "example@email.com",
            name = "Title #$id",
            body = "Comment $id"
        );
    }
}