package com.tashuseyin.kukacase.domain.repository

import com.tashuseyin.kukacase.data.model.outfits.Outfits
import com.tashuseyin.kukacase.data.model.product.CategoryProducts
import com.tashuseyin.kukacase.data.model.product.CategoryProductsItem

interface KukaRepository {

    suspend fun getCategories(): Array<String>

    suspend fun getProductListByCategory(
        category: String,
        sort: String?
    ): CategoryProducts

    suspend fun getProductDetail(id: Int): CategoryProductsItem

    suspend fun getOutfits(): Outfits
}