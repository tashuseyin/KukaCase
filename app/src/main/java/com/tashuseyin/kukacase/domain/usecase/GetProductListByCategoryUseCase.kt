package com.tashuseyin.kukacase.domain.usecase

import com.tashuseyin.kukacase.common.extension.tryFlowOrEmitError
import com.tashuseyin.kukacase.common.util.DataResult
import com.tashuseyin.kukacase.data.mapper.toUIModel
import com.tashuseyin.kukacase.domain.model.ProductItemUIModel
import com.tashuseyin.kukacase.domain.repository.KukaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductListByCategoryUseCase @Inject constructor(
    private val kukaRepository: KukaRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getProductListByCategory(category: String, sort: String?): Flow<DataResult<ArrayList<ProductItemUIModel>>> = flow {
        tryFlowOrEmitError(dispatcher) {
            kukaRepository.getProductListByCategory(category, sort).map { it.toUIModel() }
        }
    }

}