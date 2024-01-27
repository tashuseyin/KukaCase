package com.tashuseyin.kukacase.data.model.outfits


import com.google.gson.annotations.SerializedName

data class DiscountLevel(
    @SerializedName("discountedPrice")
    val discountedPrice: Double?,
    @SerializedName("level")
    val level: Int?
)