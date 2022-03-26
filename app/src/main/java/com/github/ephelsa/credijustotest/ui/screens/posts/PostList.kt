package com.github.ephelsa.credijustotest.ui.screens.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.ui.utils.PostCallback

@Composable
fun PostList(posts: List<Post>, onPostClick: PostCallback) {
    LazyColumn {
        items(posts) { post ->
            PostItem(post) { onPostClick(post) }
            Divider(modifier = Modifier.padding(horizontal = 26.dp))
        }
    }
}