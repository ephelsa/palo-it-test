package com.github.ephelsa.credijustotest.datasource.remote.json

import com.github.ephelsa.credijustotest.domain.User
import com.github.ephelsa.credijustotest.domain.DomainMapper
import kotlinx.serialization.Serializable

/**
 * JSON entity to back the data returned for the [User] model.
 *
 * Implements [DomainMapper] to map directly to [User].
 */
@Serializable
data class UserJSON(
    val id: Int,
    val name: String,
    val username: String,
): DomainMapper<User> {
    override fun toDomain(): User {
        return User(
            id = id,
            name = name,
            username = username,
        )
    }
}
