package com.github.ephelsa.credijustotest.model

import android.os.Bundle
import android.os.Parcelable
import com.github.ephelsa.credijustotest.domain.DomainMapper
import com.github.ephelsa.credijustotest.domain.Post
import kotlinx.parcelize.Parcelize

/**
 * This implementation helps to send a [Post] via [Bundle].
 *
 * Call [PostParcelable.fromDomain] to transform from [Post] to [PostParcelable]; or call
 * [toDomain] to transform an instance of [PostParcelable] to [Post].
 *
 * @see DomainMapper
 * @see UserParcelable
 */
@Parcelize
class PostParcelable(
    val id: Int,
    val user: UserParcelable,
    val title: String,
    val body: String,
) : Parcelable, DomainMapper<Post> {

    companion object {
        fun fromDomain(post: Post): PostParcelable {
            return PostParcelable(
                id = post.id,
                user = UserParcelable.fromDomain(post.user),
                title = post.title,
                body = post.body
            )
        }
    }

    override fun toDomain(): Post = Post(
        id = id,
        user = user.toDomain(),
        title = title,
        body = body,
    )
}
