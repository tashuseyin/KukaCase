package com.tashuseyin.kukacase.features.product_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tashuseyin.kukacase.common.util.DataResult
import com.tashuseyin.kukacase.common.util.Sort
import com.tashuseyin.kukacase.common.util.updateSort
import com.tashuseyin.kukacase.domain.usecase.GetProductListByCategoryUseCase
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
class ProductListViewModel @Inject constructor(
    private val getProductListByCategoryUseCase: GetProductListByCategoryUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductListUIState> =
        MutableStateFlow(ProductListUIState())
    val uiState: StateFlow<ProductListUIState> = _uiState.asStateFlow()
    var sort: Sort = Sort.NONE
        private set

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            _uiState.update { state -> state.copy(category = category) }
            getCategories(category, sort.value)
        }
    }

    fun updateSort(sort: Sort) {
        this.sort = sort.updateSort()
        getCategories(category = _uiState.value.category, sort = this.sort.value)
    }

    private fun getCategories(category: String, sort: String?) {
        viewModelScope.launch {
            getProductListByCategoryUseCase.getProductListByCategory(category, sort)
                .onStart { _uiState.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _uiState.update { state -> state.copy(isLoading = false) } }
                .collect { dataResult ->
                    when (dataResult) {
                        is DataResult.Success -> {
                            _uiState.update { state ->
                                state.copy(productList = dataResult.data)
                            }
                        }

                        is DataResult.Error -> {}
                    }
                }
        }
    }
}