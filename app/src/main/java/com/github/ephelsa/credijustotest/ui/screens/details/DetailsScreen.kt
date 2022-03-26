package com.github.ephelsa.credijustotest.ui.screens.details

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.github.ephelsa.credijustotest.R
import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.ui.components.ErrorIndicator
import com.github.ephelsa.credijustotest.ui.theme.BattleShipGray
import com.github.ephelsa.credijustotest.ui.theme.MediumHighSpace
import com.github.ephelsa.credijustotest.ui.theme.MediumSpace
import com.github.ephelsa.credijustotest.ui.theme.SmallHighSpace
import com.github.ephelsa.credijustotest.ui.utils.Constants
import com.github.ephelsa.credijustotest.ui.utils.VoidCallback
import com.github.ephelsa.credijustotest.ui.viewstate.ViewState

@ExperimentalFoundationApi
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBackClick: VoidCallback,
) {
    val postViewState: ViewState<Post> by viewModel.onPost.collectAsState()
    val commentsViewState: ViewState<List<Comment>> by viewModel.onComments.collectAsState()

    Scaffold(
        topBar = {
            Header(
                postViewState = postViewState,
                onBackClick = onBackClick,
            )
        },
    ) { _ ->
        Column(
            modifier = Modifier.padding(
                horizontal = MediumSpace,
                vertical = MediumHighSpace,
            )
        ) {
            Body(postViewState = postViewState)
            Divider(
                modifier = Modifier.padding(
                    top = MediumHighSpace,
                    bottom = MediumSpace,
                ),
            )
            Comments(commentsViewState = commentsViewState)
        }
    }
}

@Composable
private fun Header(postViewState: ViewState<Post>, onBackClick: VoidCallback) {
    Row(
        modifier = Modifier
            .padding(vertical = MediumSpace)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.Top,
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Rounded.KeyboardArrowLeft,
                contentDescription = stringResource(id = R.string.contentDescription_navigateToBack),
                tint = MaterialTheme.colors.primary
            )
        }

        when (postViewState) {
            is ViewState.Error -> Log.e(Constants.Tag.ERROR_TAG, postViewState.throwable.toString())
            is ViewState.Loading, is ViewState.Initialized -> CircularProgressIndicator()
            is ViewState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MediumSpace),
                ) {
                    Text(
                        text = postViewState.data.title,
                        style = MaterialTheme.typography.h4,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = SmallHighSpace),
                        text = buildAnnotatedString {
                            append("${stringResource(id = R.string.label_by)} ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(postViewState.data.user.name)
                            }
                        },
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Right,
                        fontStyle = FontStyle.Italic,
                        color = BattleShipGray,
                    )
                }
            }
        }
    }
}

@Composable
private fun Body(postViewState: ViewState<Post>) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        when (postViewState) {
            is ViewState.Error -> Log.e(Constants.Tag.ERROR_TAG, postViewState.throwable.toString())
            is ViewState.Loading, is ViewState.Initialized -> CircularProgressIndicator()
            is ViewState.Success -> Text(
                modifier = Modifier.fillMaxWidth(),
                text = postViewState.data.body,
                textAlign = TextAlign.Left,
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun Comments(commentsViewState: ViewState<List<Comment>>) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (commentsViewState) {
            is ViewState.Error -> ErrorIndicator()
            is ViewState.Loading, is ViewState.Initialized -> CircularProgressIndicator()
            is ViewState.Success -> {
                CommentList(comments = commentsViewState.data)
            }
        }
    }
}