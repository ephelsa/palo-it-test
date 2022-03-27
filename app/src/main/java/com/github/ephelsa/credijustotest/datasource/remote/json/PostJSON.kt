package com.github.ephelsa.credijustotest.datasource.remote.json

import com.github.ephelsa.credijustotest.domain.Post
import kotlinx.serialization.Serializable

/**
 * JSON entity to back the data returned for the [Post] model.
 */
@Serializable
data class PostJSON(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)