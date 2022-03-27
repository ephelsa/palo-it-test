package com.github.ephelsa.credijustotest.datasource.remote.json

import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.domain.DomainMapper
import kotlinx.serialization.Serializable

/**
 * JSON entity to back the data returned for the [Comment] model.
 *
 * Implements [DomainMapper] to map directly to [Comment].
 */
@Serializable
data class CommentJSON(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
) : DomainMapper<Comment> {

    override fun toDomain(): Comment {
        return Comment(
            postId = postId,
            id = id,
            name = name,
            email = email,
            body = body
        )
    }
}
