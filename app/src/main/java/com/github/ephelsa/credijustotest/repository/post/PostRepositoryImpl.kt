package com.github.ephelsa.credijustotest.repository.post

import com.github.ephelsa.credijustotest.datasource.remote.post.RemotePostDatasource
import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * _Real_ implementation of [PostRepository] that manipulate the information from data sources.
 *
 * @param dispatcher see [PostRepository] for more information.
 * @param remotePostDatasource any implementation of [RemotePostDatasource] to retrieve
 * post information from an API calls.
 */
class PostRepositoryImpl(
    override val dispatcher: CoroutineDispatcher,
    private val remotePostDatasource: RemotePostDatasource,
) : PostRepository {
    override suspend fun fetchPosts(): Result<List<Post>> {
        return try {
            val response = coroutineScope { async(dispatcher) { remotePostDatasource.fetchPosts() } }
            val mapped = response.await().map { json ->
                Post(
                    id = json.id,
                    // TODO: Replace this hard-coded User when this repository exist
                    user = User(
                        id = json.userId,
                        name = "Fake name",
                        username = "fake.username",
                    ),
                    title = json.title,
                    body = json.body,
                )
            }

            Result.success(mapped)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun fetchCommentsByPost(postId: Int): Result<List<Comment>> {
        TODO("Not yet implemented")
    }
}