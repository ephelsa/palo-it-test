package com.github.ephelsa.credijustotest.datasource.remote.post

import com.github.ephelsa.credijustotest.datasource.remote.json.CommentJSON
import com.github.ephelsa.credijustotest.datasource.remote.json.PostJSON
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Fake implementation of [RemotePostDatasource] useful for tests or development environment.
 *
 * @param dispatcher see [RemotePostDatasource] for more information.
 * @param numberOfData when a method returns a list of something, this describes how many
 * items are required.
 */
class FakeRemotePostDatasource(
    override val dispatcher: CoroutineDispatcher,
    private val numberOfData: Int,
) : RemotePostDatasource {
    override suspend fun fetchPosts(): List<PostJSON> = withContext(dispatcher) {
        delay(1_000)
        (1..numberOfData).map(::createPost)
    }

    override suspend fun fetchCommentsByPost(postId: Int): List<CommentJSON> = withContext(dispatcher) {
        delay(1_000)
        (1..numberOfData).map { id ->
            createComment(postId, id)
        }
    }

    private fun createPost(id: Int): PostJSON {
        return PostJSON(
            userId = id,
            id = id,
            title = "Post #$id",
            body = "Body #$id"
        )
    }

    private fun createComment(postId: Int, id: Int): CommentJSON {
        return CommentJSON(
            postId = postId,
            id = id,
            email = "fake@email.com",
            name = "Comment #$id",
            body = "Comment's body #$id"
        )
    }
}