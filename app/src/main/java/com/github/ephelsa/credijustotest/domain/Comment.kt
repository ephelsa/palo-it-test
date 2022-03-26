package com.github.ephelsa.credijustotest.domain

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)
