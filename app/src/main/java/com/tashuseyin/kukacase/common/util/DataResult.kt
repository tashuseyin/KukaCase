package com.tashuseyin.kukacase.common.util

sealed class DataResult<T> {
    class Success<T>(val data: T) : DataResult<T>()
    class Error<T>(val message: String) : DataResult<T>()
}