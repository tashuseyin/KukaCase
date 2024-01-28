package com.tashuseyin.kukacase.data.service

import com.tashuseyin.kukacase.data.model.product.CategoryProducts
import com.tashuseyin.kukacase.data.model.product.CategoryProductsItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApiService {

    @GET("products/categories")
    suspend fun getCategories(): Array<String>

    @GET("products/category/{category}")
    suspend fun getProductListByCategory(
        @Path("category") category: String,
        @Query("sort") sort: String? = null,
    ): CategoryProducts

    @GET("products/{id}")
    suspend fun getProductDetail(
        @Path("id") id: Int,
    ): CategoryProductsItem
}