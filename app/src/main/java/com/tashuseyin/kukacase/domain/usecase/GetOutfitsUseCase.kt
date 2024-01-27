package com.tashuseyin.kukacase.domain.usecase

import com.tashuseyin.kukacase.common.extension.tryFlowOrEmitError
import com.tashuseyin.kukacase.common.util.DataResult
import com.tashuseyin.kukacase.data.mapper.toUIModel
import com.tashuseyin.kukacase.domain.model.OutfitsItemUIModel
import com.tashuseyin.kukacase.domain.repository.KukaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOutfitsUseCase @Inject constructor(
    private val kukaRepository: KukaRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
){

    suspend fun getOutfits(): Flow<DataResult<List<OutfitsItemUIModel>>> = flow {
        tryFlowOrEmitError(dispatcher) {
            kukaRepository.getOutfits().map { it.toUIModel() }
        }
    }

}