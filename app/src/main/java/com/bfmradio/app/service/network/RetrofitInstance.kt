package com.bfmradio.app.service.network

import com.bfmradio.app.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit  by lazy {
        Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PodcastApiService by lazy {
        retrofit.create(PodcastApiService::class.java)
    }
}