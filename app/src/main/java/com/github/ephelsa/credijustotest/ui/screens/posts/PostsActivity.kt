package com.github.ephelsa.credijustotest.ui.screens.posts

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.model.PostParcelable
import com.github.ephelsa.credijustotest.ui.screens.details.DetailsActivity
import com.github.ephelsa.credijustotest.ui.theme.CredijustoTestTheme
import com.github.ephelsa.credijustotest.ui.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalFoundationApi
@AndroidEntryPoint
class PostsActivity : ComponentActivity() {

    @Inject
    internal lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CredijustoTestTheme {
                PostsScreen(viewModel = viewModel, onPostClick = ::startDetailsActivity)
            }
        }
    }

    private fun startDetailsActivity(post: Post) {
        val bundle = Bundle().apply {
            putParcelable(Constants.Extra.POST_EXTRA, PostParcelable.fromDomain(post))
        }
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchPosts()
    }
}
