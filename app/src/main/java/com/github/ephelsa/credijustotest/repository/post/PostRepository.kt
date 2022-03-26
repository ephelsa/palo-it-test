package com.github.ephelsa.credijustotest.repository.post

import com.github.ephelsa.credijustotest.domain.Post

/**
 * Repository to manipulate [Post] stuff related.
 */
interface PostRepository {

    /**
     * Fetch a list of [Post] in a safety way wrapping the results in [Result].
     *
     * @see Result for more information.
     */
    suspend fun fetchPosts(): Result<List<Post>>
}