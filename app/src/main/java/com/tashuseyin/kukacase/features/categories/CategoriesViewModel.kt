package com.tashuseyin.kukacase.features.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tashuseyin.kukacase.common.util.DataResult
import com.tashuseyin.kukacase.domain.usecase.GetCategoriesUseCase
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
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<CategoriesUIState> =
        MutableStateFlow(CategoriesUIState())
    val uiState: StateFlow<CategoriesUIState> = _uiState.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.getCategories()
                .onStart { _uiState.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _uiState.update { state -> state.copy(isLoading = false) } }
                .collect { dataResult ->
                    when (dataResult) {
                        is DataResult.Success -> {
                            _uiState.update { state ->
                                state.copy(categoriesList = dataResult.data.toList())
                            }
                        }
                        is DataResult.Error -> {}
                    }
                }
        }
    }
}