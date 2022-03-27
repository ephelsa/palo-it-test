package com.github.ephelsa.credijustotest.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ephelsa.credijustotest.R
import com.github.ephelsa.credijustotest.ui.theme.LightGray
import com.github.ephelsa.credijustotest.ui.utils.Constants

/**
 * Error composable to display when something unexpected occurs interacting with data.
 */
@Composable
fun ErrorIndicator(e: Throwable?) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            imageVector = Icons.Rounded.Close,
            contentDescription = stringResource(id = R.string.contentDescription_errorIndicator),
            tint = LightGray
        )
        Text(
            text = stringResource(id = R.string.contentDescription_errorIndicator),
            color = LightGray,
        )

        Log.e(Constants.Tag.ERROR_TAG, "Exception from ErrorIndicator", e)
    }
}