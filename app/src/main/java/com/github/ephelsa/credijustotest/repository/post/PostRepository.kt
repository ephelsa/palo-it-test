package com.github.ephelsa.credijustotest.repository.post

import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.domain.Post
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Repository to manipulate post stuff related.
 */
interface PostRepository {
    /**
     * [CoroutineDispatcher] to confine the coroutine executions to a specific thread.
     *
     * Useful for unit tests too.
     */
    val dispatcher: CoroutineDispatcher


    /**
     * Fetch a list of [Post] in a safety way wrapping the results in [Result].
     *
     * @see Result for more information.
     */
    suspend fun fetchPosts(): Result<List<Post>>

    /**
     * Fetch a list of [Comment] for a `post id` in a safety way wrapping the results in [Result].
     *
     * @param postId ID of the post to fetch the comments.
     */
    suspend fun fetchCommentsByPost(postId: Int): Result<List<Comment>>
}