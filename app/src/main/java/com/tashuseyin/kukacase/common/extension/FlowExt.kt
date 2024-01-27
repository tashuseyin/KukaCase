package com.tashuseyin.kukacase.common.extension

import com.tashuseyin.kukacase.common.util.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> tryFlowOrEmitError(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    block: suspend () -> T,
) = flow {
    try {
        emit(DataResult.Success(block()))
    } catch (e: Exception) {
        emit(DataResult.Error(e))
    }
}.flowOn(dispatcher)