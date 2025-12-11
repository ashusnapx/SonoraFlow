package com.sonoraflow.feature.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonoraflow.core.audio.player.AudioPlayer
import com.sonoraflow.core.audio.player.PlayerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val audioPlayer: AudioPlayer
) : ViewModel() {

    val playerState: StateFlow<PlayerState> = audioPlayer.playerState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PlayerState()
        )

    fun onPlayPauseClick() {
        if (playerState.value.isPlaying) {
            audioPlayer.pause()
        } else {
            audioPlayer.resume()
        }
    }

    fun onPreviousClick() {
        audioPlayer.skipToPrevious()
    }

    fun onNextClick() {
        audioPlayer.skipToNext()
    }

    fun onSeek(position: Long) {
        audioPlayer.seekTo(position)
    }
}
