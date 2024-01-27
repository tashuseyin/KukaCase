package com.tashuseyin.kukacase.data.service

import com.tashuseyin.kukacase.data.model.outfits.Outfits
import retrofit2.http.GET

interface OutfitsApiService {
    @GET("muratyilmaz/case/outfits")
    suspend fun getOutfits(): Outfits
}