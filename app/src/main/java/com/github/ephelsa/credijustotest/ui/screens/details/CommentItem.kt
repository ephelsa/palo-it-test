package com.github.ephelsa.credijustotest.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.github.ephelsa.credijustotest.domain.Comment

@Composable
fun CommentItem(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp)
    ) {
        Text(
            text = comment.name,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            text = comment.body,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = comment.email,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.secondary,
                textDecoration = TextDecoration.Underline,
            ),
        )
    }
}