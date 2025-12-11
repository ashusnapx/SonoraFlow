package com.sonoraflow.core.audio.player

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface AudioPlayer {
    val playerState: StateFlow<PlayerState>
    fun play(mediaItems: List<MediaItem>, startIndex: Int = 0)
    fun pause()
    fun resume()
    fun seekTo(position: Long)
    fun skipToNext()
    fun skipToPrevious()
    fun release()
}

data class PlayerState(
    val isPlaying: Boolean = false,
    val currentMediaItem: MediaItem? = null,
    val playbackState: Int = Player.STATE_IDLE,
    val duration: Long = 0L,
    val currentPosition: Long = 0L
)

class SonoraAudioPlayer @Inject constructor(
    private val exoPlayer: ExoPlayer
) : AudioPlayer {

    private val _playerState = MutableStateFlow(PlayerState())
    override val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    private val listener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            updateState()
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            updateState()
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            updateState()
        }
    }

    init {
        exoPlayer.addListener(listener)
    }

    override fun play(mediaItems: List<MediaItem>, startIndex: Int) {
        exoPlayer.setMediaItems(mediaItems, startIndex, 0L)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun resume() {
        exoPlayer.play()
    }

    override fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    override fun skipToNext() {
        if (exoPlayer.hasNextMediaItem()) {
            exoPlayer.seekToNextMediaItem()
        }
    }

    override fun skipToPrevious() {
        if (exoPlayer.hasPreviousMediaItem()) {
            exoPlayer.seekToPreviousMediaItem()
        }
    }

    override fun release() {
        exoPlayer.removeListener(listener)
        exoPlayer.release()
    }

    private fun updateState() {
        _playerState.value = PlayerState(
            isPlaying = exoPlayer.isPlaying,
            currentMediaItem = exoPlayer.currentMediaItem,
            playbackState = exoPlayer.playbackState,
            duration = exoPlayer.duration.takeIf { it > 0 } ?: 0L,
            currentPosition = exoPlayer.currentPosition
        )
    }
}
