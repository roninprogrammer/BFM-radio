package com.bfmradio.app.domain.model

import com.bfmradio.app.data.model.PodcastData

data class PodcastDomainModel(
    val title: String,
    val subtitle: String?,
    val type: String,
    val source: String,
    val data: PodcastData?
)