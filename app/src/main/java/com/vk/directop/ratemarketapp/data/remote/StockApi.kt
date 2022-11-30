package com.vk.directop.ratemarketapp.data.remote

import com.vk.directop.ratemarketapp.BuildConfig
import retrofit2.http.Query

interface StockApi {

    suspend fun getListings(
        @Query("apikey") apiKey: String
    )

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

}