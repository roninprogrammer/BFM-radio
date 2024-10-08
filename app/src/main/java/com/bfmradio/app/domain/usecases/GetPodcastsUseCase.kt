package com.bfmradio.app.domain.usecases

import com.bfmradio.app.domain.model.PodcastDomainModel
import android.os.Build
import androidx.annotation.RequiresApi
import com.bfmradio.app.service.repository.PodcastRepository
import javax.inject.Inject

class GetPodcastsUseCase @Inject constructor(private val repository: PodcastRepository) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun execute(): List<PodcastDomainModel> {
        val podcasts = repository.getPodcasts().body() ?: emptyList()

        return podcasts.map { podcast ->
            PodcastDomainModel(
                title = podcast.title,
                subtitle = podcast.subtitle,
                type = podcast.type,
                source = podcast.source,
                data = podcast.data
            )
        }
    }
}
