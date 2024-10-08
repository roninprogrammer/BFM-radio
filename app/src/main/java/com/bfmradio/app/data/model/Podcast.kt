package com.bfmradio.app.data.model

import kotlinx.serialization.SerialName

data class Podcast(
    @SerialName("title")
    val title: String,

    @SerialName("subtitle")
    val subtitle: String? = null,

    @SerialName("type")
    val type: String,

    @SerialName("source")
    val source: String,

    @SerialName("data")
    val data: PodcastData? = null
)
