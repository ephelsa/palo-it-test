package com.github.ephelsa.credijustotest.datasource.remote.post

import com.github.ephelsa.credijustotest.datasource.remote.json.CommentJSON
import com.github.ephelsa.credijustotest.datasource.remote.json.PostJSON
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemotePostDatasourceImpl(
    override val dispatcher: CoroutineDispatcher,
    private val client: HttpClient,
) : RemotePostDatasource {

    override suspend fun fetchPosts(): List<PostJSON> {
        return withContext(dispatcher) {
            client.get("/posts")
        }
    }

    override suspend fun fetchCommentsByPost(postId: Int): List<CommentJSON> {
        return withContext(dispatcher) {
            client.get("/posts/$postId/comments")
        }
    }
}