package com.github.ephelsa.credijustotest.ui.screens.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.model.PostParcelable
import com.github.ephelsa.credijustotest.ui.theme.CredijustoTestTheme
import com.github.ephelsa.credijustotest.ui.utils.Constants

@ExperimentalFoundationApi
class DetailsActivity : ComponentActivity() {

    private val viewModel: DetailsViewModel by viewModels()
    private val post: Post
        get() = intent.extras!!.getParcelable<PostParcelable>(Constants.Extra.POST_EXTRA)!!.toDomain()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CredijustoTestTheme {
                DetailsScreen(
                    viewModel = viewModel,
                    onBackClick = { finish() },
                )
            }
        }

        viewModel.emitPostInformation(post)
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchComments(post.id)
    }
}