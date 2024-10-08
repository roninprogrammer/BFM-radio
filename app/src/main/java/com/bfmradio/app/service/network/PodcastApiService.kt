package com.bfmradio.app.service.network

import com.bfmradio.app.data.model.Podcast
import retrofit2.Response
import retrofit2.http.GET

interface PodcastApiService{
    @GET("tests/endpoint.json")
    suspend fun getPodcasts() : Response<List<Podcast>>
}