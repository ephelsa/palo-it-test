package com.github.ephelsa.credijustotest.repository.post

import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.domain.Post

/**
 * Repository to manipulate post stuff related.
 */
interface PostRepository {

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
    suspend fun fetchComments(postId: Int): Result<List<Comment>>
}