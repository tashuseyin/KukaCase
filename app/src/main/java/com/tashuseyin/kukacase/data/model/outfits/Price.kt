package com.tashuseyin.kukacase.data.model.outfits

import com.google.gson.annotations.SerializedName


data class Price(
    @SerializedName("discountLevels")
    val discountLevels: List<DiscountLevel?>?,
    @SerializedName("originalPrice")
    val originalPrice: Double?
)