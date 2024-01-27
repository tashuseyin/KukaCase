package com.tashuseyin.kukacase.domain.usecase

import com.tashuseyin.kukacase.common.extension.tryFlowOrEmitError
import com.tashuseyin.kukacase.common.util.DataResult
import com.tashuseyin.kukacase.di.IoDispatcher
import com.tashuseyin.kukacase.domain.repository.KukaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val kukaRepository: KukaRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    fun getCategories(): Flow<DataResult<Array<String>>> =
        tryFlowOrEmitError(dispatcher) { kukaRepository.getCategories() }
}