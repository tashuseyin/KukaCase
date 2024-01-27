package com.tashuseyin.kukacase.features.categories

data class CategoriesUIState(
    val isLoading: Boolean = false,
    val categoriesList: List<String> = emptyList()
)