package com.github.ephelsa.credijustotest.ui.screens.posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.github.ephelsa.credijustotest.R
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.ui.components.ErrorIndicator
import com.github.ephelsa.credijustotest.ui.theme.MediumHighSpace
import com.github.ephelsa.credijustotest.ui.theme.MediumSpace
import com.github.ephelsa.credijustotest.ui.utils.PostCallback
import com.github.ephelsa.credijustotest.ui.viewstate.ViewState

@Composable
fun PostsScreen(viewModel: PostsViewModel, onPostClick: PostCallback) {
    val state: ViewState<List<Post>> by viewModel.onPosts.collectAsState()

    Scaffold(
        topBar = { Header() }
    ) { _ ->
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            when (state) {
                is ViewState.Error -> ErrorIndicator((state as ViewState.Error).throwable)
                is ViewState.Loading, is ViewState.Initialized -> CircularProgressIndicator()
                is ViewState.Success -> {
                    PostList(
                        posts = (state as ViewState.Success<List<Post>>).data,
                        onPostClick = onPostClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MediumSpace,
                vertical = MediumHighSpace,
            )
    ) {
        val welcomeText = stringResource(id = R.string.label_welcome)

        Text(
            modifier = Modifier.padding(
                bottom = MediumSpace,
            ),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                    append(welcomeText[0])
                }
                append(welcomeText.substring(1))
            },
            style = MaterialTheme.typography.h4,
        )
        Text(
            text = stringResource(id = R.string.label_welcomeDescription),
            style = MaterialTheme.typography.subtitle1,
        )
    }
}
