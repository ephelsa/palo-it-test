package com.github.ephelsa.credijustotest.datasource.remote.post

import com.github.ephelsa.credijustotest.datasource.remote.json.PostJSON
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Remote datasource for posts.
 *
 * For a _real_ based implementation, see [RemotePostDatasourceImpl].
 *
 * @see RemotePostDatasourceImpl
 */
interface RemotePostDatasource {
    /**
     * [CoroutineDispatcher] to confine the coroutine executions to a specific thread.
     *
     * Useful for unit tests too.
     */
    val dispatcher: CoroutineDispatcher

    /**
     * Fetch a list of [PostJSON].
     */
    suspend fun fetchPosts(): List<PostJSON>

    /**
     * Fetch a list of [CommentJSON] based on a [postId].
     *
     * @param postId id of a post
     */
    suspend fun fetchCommentsByPost(postId: Int)
}