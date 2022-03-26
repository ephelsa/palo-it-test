package com.github.ephelsa.credijustotest.model

import android.os.Bundle
import android.os.Parcelable
import com.github.ephelsa.credijustotest.domain.DomainMapper
import com.github.ephelsa.credijustotest.domain.User
import kotlinx.parcelize.Parcelize

/**
 * This implementation helps to send a [User] via [Bundle].
 *
 * Call [UserParcelable.fromDomain] to transform from [User] to [UserParcelable]; or call
 * [toDomain] to transform an instance of [UserParcelable] to [User].
 *
 * @see DomainMapper
 * @see UserParcelable
 */
@Parcelize
class UserParcelable(
    val id: Int,
    val name: String,
    val username: String,
) : Parcelable, DomainMapper<User> {

    companion object {
        fun fromDomain(user: User): UserParcelable {
            return UserParcelable(
                id = user.id,
                name = user.name,
                username = user.username
            )
        }
    }

    override fun toDomain(): User = User(
        id = id,
        name = name,
        username = username,
    )
}