package com.github.ephelsa.credijustotest.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.credijustotest.domain.Comment
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.repository.post.PostRepository
import com.github.ephelsa.credijustotest.ui.viewstate.ViewState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for [DetailsActivity]
 */
class DetailsViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ViewModel() {
    private val _post = MutableStateFlow<ViewState<Post>>(ViewState.Initialized())
    val onPost: StateFlow<ViewState<Post>>
        get() = _post

    private val _comments = MutableStateFlow<ViewState<List<Comment>>>(ViewState.Initialized())
    val onComments: StateFlow<ViewState<List<Comment>>>
        get() = _comments

    fun emitPostInformation(post: Post) {
        _post.tryEmit(ViewState.Success(post))
    }

    fun fetchComments(postId: Int) {
        viewModelScope.launch {
            _comments.emit(ViewState.Loading())

            val result = postRepository.fetchComments(postId)

            result.fold(
                onSuccess = { _comments.emit(ViewState.Success(it)) },
                onFailure = { _comments.emit(ViewState.Error(it)) }
            )
        }
    }
}