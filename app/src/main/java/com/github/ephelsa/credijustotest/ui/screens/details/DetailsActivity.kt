package com.github.ephelsa.credijustotest.ui.screens.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.ui.model.PostParcelable
import com.github.ephelsa.credijustotest.ui.theme.CredijustoTestTheme
import com.github.ephelsa.credijustotest.ui.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalFoundationApi
@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    @Inject
    internal lateinit var viewModel: DetailsViewModel
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