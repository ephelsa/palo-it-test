package com.github.ephelsa.credijustotest.ui.screens.posts

import app.cash.turbine.test
import com.github.ephelsa.credijustotest.domain.Post
import com.github.ephelsa.credijustotest.repository.post.FakePostRepository
import com.github.ephelsa.credijustotest.repository.post.PostRepository
import com.github.ephelsa.credijustotest.ui.viewstate.ViewState
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalTime
@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
internal class PostsViewModelTest {

    @MockK
    lateinit var mockPostRepository: PostRepository

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `When everything goes ok, fetchPosts emits a List of Posts`() = runTest {
        // Given
        val viewModel = PostsViewModel(
            postRepository = FakePostRepository(
                dispatcher = Dispatchers.Main,
                numberOfData = 10,
            )
        )

        // When
        viewModel.fetchPosts()

        // Then
        viewModel.onPosts.test(timeoutMs = 2_000L) {
            awaitItem().shouldBeInstanceOf<ViewState.Initialized<List<Post>>>()
            awaitItem().shouldBeInstanceOf<ViewState.Loading<List<Post>>>()
            awaitItem().shouldBeInstanceOf<ViewState.Success<List<Post>>>().data.shouldHaveSize(10)
        }
    }

    @Test
    fun `When an error occurs, fetchPosts emits a ViewState#Error`() = runTest {
        // Given
        val viewModel = PostsViewModel(
            postRepository = mockPostRepository,
        )
        val exception = Exception("Something wrong occurred retrieving posts")

        coEvery { mockPostRepository.fetchPosts() } returns Result.failure(exception)

        // When
        viewModel.fetchPosts()

        // Then
        viewModel.onPosts.test {
            awaitItem().shouldBeInstanceOf<ViewState.Initialized<List<Post>>>()
            awaitItem().shouldBeInstanceOf<ViewState.Loading<List<Post>>>()
            awaitItem().shouldBeInstanceOf<ViewState.Error<List<Post>>>().throwable.shouldBe(exception)
        }
    }
}