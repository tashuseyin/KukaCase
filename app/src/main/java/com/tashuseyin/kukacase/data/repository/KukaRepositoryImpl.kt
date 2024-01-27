package com.tashuseyin.kukacase.data.repository

import com.tashuseyin.kukacase.data.model.outfits.Outfits
import com.tashuseyin.kukacase.data.model.product.CategoryProducts
import com.tashuseyin.kukacase.data.model.product.CategoryProductsItem
import com.tashuseyin.kukacase.data.service.OutfitsApiService
import com.tashuseyin.kukacase.data.service.ProductsApiService
import com.tashuseyin.kukacase.domain.repository.KukaRepository
import javax.inject.Inject

class KukaRepositoryImpl @Inject constructor(
    private val productsApiService: ProductsApiService,
    private val outfitsApiService: OutfitsApiService,
) : KukaRepository {

    override suspend fun getCategories(): Array<String> {
        return productsApiService.getCategories()
    }

    override suspend fun getProductListByCategory(
        category: String,
        sort: String?,
    ): CategoryProducts {
        return productsApiService.getProductListByCategory(category, sort)
    }

    override suspend fun getProductDetail(id: String): CategoryProductsItem {
        return productsApiService.getProductDetail(id)
    }

    override suspend fun getOutfits(): Outfits {
        return outfitsApiService.getOutfits()
    }
}
