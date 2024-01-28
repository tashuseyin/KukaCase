package com.tashuseyin.kukacase.features.product_detail

import com.tashuseyin.kukacase.domain.model.OutfitsItemUIModel
import com.tashuseyin.kukacase.domain.model.ProductItemUIModel

data class ProductDetailUIState(
    val isLoading: Boolean = false,
    val outfitsList: List<OutfitsItemUIModel> = emptyList(),
    val productItemUIModel: ProductItemUIModel? = null
)