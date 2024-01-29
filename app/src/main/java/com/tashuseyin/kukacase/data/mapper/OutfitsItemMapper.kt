package com.tashuseyin.kukacase.data.mapper

import com.tashuseyin.kukacase.data.model.outfits.OutfitsItem
import com.tashuseyin.kukacase.domain.model.OutfitsItemUIModel

fun OutfitsItem.toUIModel(): OutfitsItemUIModel {
    return OutfitsItemUIModel(
        id = this.id,
        image = this.image,
        title = this.title,
        ratingRate = this.rating?.rate,
        ratingCount = this.rating?.count,
        originalPrice = this.price?.originalPrice,
        discountLevels = this.price?.discountLevels,
        discountPrice = null
    )
}