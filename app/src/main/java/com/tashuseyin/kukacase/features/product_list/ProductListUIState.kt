package com.tashuseyin.kukacase.features.product_list

import com.tashuseyin.kukacase.domain.model.ProductItemUIModel

data class ProductListUIState(
    val isLoading: Boolean = false,
    val category: String = "",
    val productList: List<ProductItemUIModel> = emptyList()
)