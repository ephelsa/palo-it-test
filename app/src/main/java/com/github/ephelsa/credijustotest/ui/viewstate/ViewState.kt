package com.github.ephelsa.credijustotest.ui.viewstate

import kotlinx.coroutines.flow.StateFlow

/**
 * Generic sealed class to wrap the common scenarios for this **simple application**.
 */
sealed class ViewState<T> {
    /**
     * Use this when you want to initialize a [StateFlow] (for example) or to
     * restore the view to the initial state, i.e. clean all the data displayed in a form.
     */
    class Initialized<T> : ViewState<T>()

    /**
     * Use this when you're doing async tasks to let the view know.
     */
    class Loading<T> : ViewState<T>()

    /**
     * Use this when something occurs in an async tasks to let the view know.
     */
    data class Error<T>(val throwable: Throwable?) : ViewState<T>()

    /**
     * Use this when your async task returns data/has finalized to let the view know.
     *
     * @property data is used by the view to display useful information to the user.
     */
    data class Success<Data>(val data: Data) : ViewState<Data>()
}
