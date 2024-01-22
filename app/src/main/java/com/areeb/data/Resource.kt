package com.areeb.data

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()

    data class Loading<out T>(val status: Boolean = false) : Resource<Nothing>()
    data class Error(val errorString: String) : Resource<Nothing>()
}