package com.github.ephelsa.credijustotest.domain

data class Post(
    val id: Int,
    val user: User,
    val title: String,
    val body: String,
)
