package org.raam.quizkmp.presentation.utils

sealed class UiStateHandler<out T> {

    object Loading : UiStateHandler<Nothing>()

    data class Success<out T>(val data: T) : UiStateHandler<T>()

    data class Error(val message: String, val throwable: Throwable? = null) : UiStateHandler<Nothing>()
}