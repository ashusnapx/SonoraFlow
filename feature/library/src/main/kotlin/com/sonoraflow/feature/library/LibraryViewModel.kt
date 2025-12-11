package com.sonoraflow.feature.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonoraflow.core.audio.player.AudioPlayer
import com.sonoraflow.core.data.repository.SongRepository
import com.sonoraflow.core.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val songRepository: SongRepository,
    private val audioPlayer: AudioPlayer
) : ViewModel() {

    val songs: StateFlow<List<Track>> = songRepository.getSongs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            songRepository.refreshSongs()
        }
    }

    fun onSongClick(track: Track) {
        // Simple implementation: Play just this song (or build a queue later)
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
