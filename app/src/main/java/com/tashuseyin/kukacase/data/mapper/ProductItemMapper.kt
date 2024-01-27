package com.tashuseyin.kukacase.data.mapper

import com.tashuseyin.kukacase.data.model.product.CategoryProductsItem
import com.tashuseyin.kukacase.domain.model.ProductItemUIModel


fun CategoryProductsItem.toUIModel(): ProductItemUIModel {
    return ProductItemUIModel(
        category =  this.category,
        description = this.description,
        id = this.id,
        image = this.image,
        price = this.price,
        ratingRate = this.rating?.rate,
        ratingCount = this.rating?.count,
        title = this.title
    )
}