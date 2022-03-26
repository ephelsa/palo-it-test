package com.github.ephelsa.credijustotest.ui.screens.posts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.github.ephelsa.credijustotest.ui.theme.CredijustoTestTheme

class PostsActivity : ComponentActivity() {
    private val viewModel: PostsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CredijustoTestTheme {
                PostsScreen(viewModel) {
                    // TODO: Navigate to next screen passing arguments
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchPosts()
    }
}
