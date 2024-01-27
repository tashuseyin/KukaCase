package com.tashuseyin.kukacase.domain.usecase

import com.tashuseyin.kukacase.common.extension.tryFlowOrEmitError
import com.tashuseyin.kukacase.data.mapper.toUIModel
import com.tashuseyin.kukacase.domain.model.ProductItemUIModel
import com.tashuseyin.kukacase.domain.repository.KukaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val kukaRepository: KukaRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
){
    suspend fun getProductDetail(id: String): Flow<ProductItemUIModel> = flow {
        tryFlowOrEmitError(dispatcher) {
            kukaRepository.getProductDetail(id).toUIModel()
        }
    }
}