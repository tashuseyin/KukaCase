package com.tashuseyin.kukacase.common.util

sealed class DataResult<T> {
    class Success<T>(data: T) : DataResult<T>()
    class Error<T>(val exception: Exception) : DataResult<T>()
}