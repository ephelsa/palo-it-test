package com.github.ephelsa.credijustotest.ui.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.github.ephelsa.credijustotest.R
import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.ui.theme.BattleShipGray

@ExperimentalFoundationApi
@Composable
fun CommentList(comments: List<Comment>) {
    val resources = LocalContext.current.resources
    val totalOfComments = comments.size

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        stickyHeader {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.surface),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("$totalOfComments ")
                    }
                    append(resources.getQuantityString(R.plurals.label_peopleCommented, totalOfComments))
                },
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.caption,
                color = BattleShipGray,
            )
        }

        items(comments) { comment ->
            CommentItem(comment)
        }
    }
}