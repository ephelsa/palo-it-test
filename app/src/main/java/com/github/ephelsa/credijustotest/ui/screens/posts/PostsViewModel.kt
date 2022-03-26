package com.github.ephelsa.credijustotest.ui.screens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.repository.post.FakePostRepository
import com.github.ephelsa.credijustotest.repository.post.PostRepository
import com.github.ephelsa.credijustotest.ui.viewstate.ViewState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for [PostsActivity]
 */
class PostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ViewModel() {
    private val _posts = MutableStateFlow<ViewState<List<Post>>>(ViewState.Initialized())
    val onPosts: StateFlow<ViewState<List<Post>>>
        get() = _posts

    /**
     * Call [PostRepository.fetchPosts] to retrieve the posts information.
     */
    fun fetchPosts() {
        viewModelScope.launch {
            _posts.emit(ViewState.Loading())

            val result = postRepository.fetchPosts()

            result.fold(
                onSuccess = { _posts.emit(ViewState.Success(it)) },
                onFailure = { _posts.emit(ViewState.Error(it)) },
            )
        }
    }
}