package com.github.ephelsa.credijustotest.ui.screens.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.ephelsa.credijustotest.R
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.ui.theme.MediumHighSpace
import com.github.ephelsa.credijustotest.ui.theme.MediumSpace
import com.github.ephelsa.credijustotest.ui.utils.VoidCallback

@Composable
fun PostItem(post: Post, onClick: VoidCallback) {
    Box(modifier = Modifier
        .clickable(onClick = onClick)
        .padding(horizontal = MediumSpace, vertical = MediumHighSpace)
        .height(40.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = post.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.contentDescription_postDetails),
                tint = MaterialTheme.colors.primary
            )
        }
    }
}