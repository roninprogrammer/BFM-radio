package com.bfmradio.app.service.repository

import com.bfmradio.app.data.model.Podcast
import com.bfmradio.app.service.network.PodcastApiService
import retrofit2.Response

class PodcastRepository(private val apiService: PodcastApiService) {
    suspend fun getPodcasts(): Response<List<Podcast>> {
        return apiService.getPodcasts()
    }
}