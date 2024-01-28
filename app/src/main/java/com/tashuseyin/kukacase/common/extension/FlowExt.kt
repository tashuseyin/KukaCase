package com.tashuseyin.kukacase.common.extension

import com.tashuseyin.kukacase.common.util.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException

fun <T> tryFlowOrEmitError(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    block: suspend () -> T,
) = flow {
    try {
        emit(DataResult.Success(block()))
    }  catch (e: HttpException) {
        emit(DataResult.Error(e.localizedMessage ?: "An unexpected error occurred"))
    } catch (e: IOException) {
        emit(DataResult.Error("Couldn't reach server. Check your internet connection."))
    }
}.flowOn(dispatcher)