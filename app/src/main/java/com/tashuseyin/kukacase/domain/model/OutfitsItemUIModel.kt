package com.tashuseyin.kukacase.domain.model

import com.tashuseyin.kukacase.data.model.outfits.DiscountLevel

data class OutfitsItemUIModel(
    val id: Int?,
    val image: String?,
    val title: String?,
    val ratingRate: Double?,
    val ratingCount: Int?,
    val originalPrice: Double?,
    var discountPrice: Double?,
    val discountLevels: List<DiscountLevel?>?,
    var isAddCart: Boolean = false
)