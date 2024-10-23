package com.bfmradio.app.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bfmradio.app.data.model.Podcast
import com.bfmradio.app.service.network.PodcastApiService
import com.bfmradio.app.service.network.RetrofitInstance
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PodcastViewModel (application: Application) : AndroidViewModel(application){

    // state to hold podcast data
    private val _podcast = MutableStateFlow<List<Podcast>>(emptyList())
    val podcasts: StateFlow<List<Podcast>> get() = _podcast



    // stateFlow to track if audio is playing
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> get() = _isPlaying


    // ExoPlayer instance for playing audio
//    private val exoPlayer: ExoPlayer by lazy {
//        ExoPlayer.Builder(application).build()
//    }

    private val exoPlayer: ExoPlayer by lazy {
        ExoPlayer.Builder(application).build().apply {
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == ExoPlayer.STATE_ENDED) {
                        _isPlaying.value = false // Update state when playback ends
                    } else if (playbackState == ExoPlayer.STATE_READY && this@apply.isPlaying) {
                        _isPlaying.value = true // Update state when audio is ready to play
                    }
                }
            })
        }
    }

    private val podCastApiService: PodcastApiService = RetrofitInstance.api

    init {
        fetchPodcasts()
    }

    // Fetch podcasts from the API
    private fun fetchPodcasts() {
        viewModelScope.launch {
            try {
                val response = podCastApiService.getPodcasts()
                if (response.isSuccessful) {
                    _podcast.value = response.body() ?: emptyList()
                } else {
                    // Handle error
                    _podcast.value = emptyList()
                }
            } catch (e: Exception) {
                // Handle exception
                _podcast.value = emptyList()
            }
        }
    }

    // Play audio
    fun playAudio(source: String) {
        val mediaItem = MediaItem.fromUri(source)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    //Pause audio
    fun pauseAudio(source: String) {
        val mediaItem = MediaItem.fromUri(source)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.pause()
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}