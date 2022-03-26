package com.github.ephelsa.credijustotest.ui.utils

import com.github.ephelsa.credijustotest.domain.Post

/**
 * Type alias for empty callbacks.
 *
 * Useful for lazy people to reduce the boilerplate and improve readability readable.
 *
 * Always optional but recommended.
 */
typealias VoidCallback = () -> Unit

/**
 * Lazy type alias to return a [Post] in a callback.
 */
typealias PostCallback = (Post) -> Unit