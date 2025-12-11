package com.sonoraflow.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonoraflow.core.ai.engine.RecommendationEngine
import com.sonoraflow.core.audio.player.AudioPlayer
import com.sonoraflow.core.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendationEngine: RecommendationEngine,
    private val audioPlayer: AudioPlayer
) : ViewModel() {

    private val _recommendations = MutableStateFlow<List<Track>>(emptyList())
    val recommendations: StateFlow<List<Track>> = _recommendations.asStateFlow()

    init {
        loadRecommendations()
    }

    private fun loadRecommendations() {
        viewModelScope.launch {
            // In real app we pass history
            _recommendations.value = recommendationEngine.getRecommendations(emptyList())
        }
    }

    fun onTrackClick(track: Track) {
        val mediaItem = MediaItem.Builder()
            .setUri(track.contentUri)
            .setMediaId(track.id)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(track.title)
                    .setArtist(track.artistName)
                    .setAlbumTitle(track.albumName)
                    .setArtworkUri(track.albumArtUri?.let { android.net.Uri.parse(it) })
                    .build()
            )
            .build()
        audioPlayer.play(listOf(mediaItem))
    }
}
