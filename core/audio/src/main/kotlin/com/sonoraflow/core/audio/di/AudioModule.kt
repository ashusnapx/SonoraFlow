package com.sonoraflow.core.audio.di

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.sonoraflow.core.audio.player.AudioPlayer
import com.sonoraflow.core.audio.player.SonoraAudioPlayer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AudioModule {

    @Provides
    @Singleton
    fun provideAudioAttributes(): AudioAttributes =
        AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()

    @Provides
    @Singleton
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ): ExoPlayer = ExoPlayer.Builder(context)
        .setAudioAttributes(audioAttributes, true)
        .setHandleAudioBecomingNoisy(true)
        .build()
        
    @Provides
    @Singleton
    fun providePlayer(exoPlayer: ExoPlayer): Player = exoPlayer
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AudioBindingModule {
    @Binds
    @Singleton
    abstract fun bindAudioPlayer(impl: SonoraAudioPlayer): AudioPlayer
}
