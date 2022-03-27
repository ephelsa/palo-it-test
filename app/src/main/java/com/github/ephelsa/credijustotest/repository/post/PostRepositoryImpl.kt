package com.github.ephelsa.credijustotest.repository.post

import com.github.ephelsa.credijustotest.datasource.remote.json.CommentJSON
import com.github.ephelsa.credijustotest.datasource.remote.json.PostJSON
import com.github.ephelsa.credijustotest.datasource.remote.post.RemotePostDatasource
import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.repository.user.UserRepository
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
    private val userRepository: UserRepository,
) : PostRepository {
    override suspend fun fetchPosts(): Result<List<Post>> {
        return try {
            val response = coroutineScope { async(dispatcher) { remotePostDatasource.fetchPosts() } }
            val mapped = response.await().map { json ->
                mapPostJSONToPost(json)
            }
            val list = mapped.map {
                it.getOrThrow()
            }

            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun mapPostJSONToPost(json: PostJSON): Result<Post> {
        val user = coroutineScope {
            async(dispatcher) {
                userRepository.findUserById(json.userId)
            }
        }

        return user.await().fold(
            onSuccess = {
                val post = Post(
                    id = json.id,
                    user = it,
                    title = json.title,
                    body = json.body
                )

                Result.success(post)
            },
            onFailure = { Result.failure(it) }
        )
    }

    override suspend fun fetchCommentsByPost(postId: Int): Result<List<Comment>> {
        return try {
            val response = coroutineScope {
                async(dispatcher) { remotePostDatasource.fetchCommentsByPost(postId) }
            }
            val mapped = response.await().map(CommentJSON::toDomain)

            return Result.success(mapped)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}