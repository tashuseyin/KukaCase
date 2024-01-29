package com.tashuseyin.kukacase.features.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tashuseyin.kukacase.common.util.DataResult
import com.tashuseyin.kukacase.domain.usecase.GetOutfitsUseCase
import com.tashuseyin.kukacase.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val getOutfitsUseCase: GetOutfitsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductDetailUIState> =
        MutableStateFlow(ProductDetailUIState())
    val uiState: StateFlow<ProductDetailUIState> = _uiState.asStateFlow()


    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            getProductDetail(id)
        }
        getOutfits()
    }



    private fun getOutfits() {
        viewModelScope.launch {
            getOutfitsUseCase.getOutfits()
                .onStart { _uiState.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _uiState.update { state -> state.copy(isLoading = false) } }
                .collect { dataResult ->
                    when (dataResult) {
                        is DataResult.Success -> {
                            _uiState.update { state ->
                                state.copy(outfitsList = dataResult.data)
                            }
                        }

                        is DataResult.Error -> {}
                    }
                }
        }
    }

    private fun getProductDetail(id: Int) {
        viewModelScope.launch {
            getProductDetailUseCase.getProductDetail(id)
                .onStart { _uiState.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _uiState.update { state -> state.copy(isLoading = false) } }
                .collect { dataResult ->
                    when (dataResult) {
                        is DataResult.Success -> {
                            _uiState.update { state ->
                                state.copy(productItemUIModel = dataResult.data)
                            }
                        }
                        is DataResult.Error -> {}
                    }
                }
        }
    }
}