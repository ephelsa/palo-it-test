package com.github.ephelsa.credijustotest.repository.post

import com.github.ephelsa.credijustotest.datasource.remote.post.FakeRemotePostDatasource
import com.github.ephelsa.credijustotest.datasource.remote.post.RemotePostDatasource
import com.github.ephelsa.credijustotest.repository.user.FakeUserRepository
import com.github.ephelsa.credijustotest.repository.user.UserRepository
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
internal class PostRepositoryImplTest {

    private val testException = Exception("Test exception")

    @MockK
    lateinit var mockRemotePostDatasource: RemotePostDatasource

    @MockK
    lateinit var mockUserRepository: UserRepository

    @Test
    fun `Verify fetchPosts returns Result#success, when everything goes ok`() = runTest {
        // Given
        val dispatcher = StandardTestDispatcher(testScheduler)
        val impl = PostRepositoryImpl(
            dispatcher = dispatcher,
            remotePostDatasource = FakeRemotePostDatasource(dispatcher, 10),
            userRepository = FakeUserRepository(dispatcher)
        )

        // When
        val result = impl.fetchPosts()

        // Then
        result.shouldBeSuccess().shouldHaveSize(10)
    }

    @Test
    fun `Verify fetchPosts returns Result#failure, when an error occurs fetching Posts from RemotePostDatasource`() = runTest {
        // Given
        val impl = PostRepositoryImpl(
            dispatcher = StandardTestDispatcher(testScheduler),
            remotePostDatasource = mockRemotePostDatasource,
            userRepository = mockUserRepository
        )

        coEvery { mockRemotePostDatasource.fetchPosts() } throws testException

        // When
        val result = impl.fetchPosts()

        // Then
        result.shouldBeFailure().shouldBe(testException)
    }

    @Test
    fun `Verify fetchPosts returns Result#failure, when an error occurs retrieving the User from UserRepository`() = runTest {
        // Given
        val dispatcher = StandardTestDispatcher(testScheduler)
        val impl = PostRepositoryImpl(
            dispatcher = dispatcher,
            remotePostDatasource = FakeRemotePostDatasource(dispatcher, 5),
            userRepository = mockUserRepository
        )

        coEvery { mockUserRepository.findUserById(any()) } throws testException

        // When
        val result = impl.fetchPosts()

        // Then
        result.shouldBeFailure().shouldBe(testException)
    }

    @Test
    fun `Verify fetchCommentsByPost returns Result#sucess, when RemotePostDatasource#fetchCommentsByPost does not throw an Exception`() = runTest {
        // Given
        val dispatcher = StandardTestDispatcher(testScheduler)
        val impl = PostRepositoryImpl(
            dispatcher = dispatcher,
            remotePostDatasource = FakeRemotePostDatasource(dispatcher, 10),
            userRepository = mockUserRepository,
        )

        // When
        val result = impl.fetchCommentsByPost(42)

        // Then
        result.shouldBeSuccess().shouldHaveSize(10)
    }

    @Test
    fun `Verify fetchCommentsByPost returns Result#failure, when something wrong occurs on RemotePostDatasource#fetchCommentsByPost and throw an Exception`() = runTest {
        // Given
        val impl = PostRepositoryImpl(
            dispatcher = StandardTestDispatcher(testScheduler),
            remotePostDatasource = mockRemotePostDatasource,
            userRepository = mockUserRepository,
        )

        coEvery { mockRemotePostDatasource.fetchCommentsByPost(any()) } throws testException

        // When
        val result = impl.fetchCommentsByPost(42)

        // Then
        result.shouldBeFailure().shouldBe(testException)
    }
}