package com.tashuseyin.kukacase.data.model.outfits

import com.google.gson.annotations.SerializedName
import com.tashuseyin.kukacase.data.model.product.Rating


data class OutfitsItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Price?,
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("title")
    val title: String?
)