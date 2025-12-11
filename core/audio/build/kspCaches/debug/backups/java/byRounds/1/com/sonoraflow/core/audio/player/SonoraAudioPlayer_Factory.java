package com.sonoraflow.core.audio.player;

import androidx.media3.exoplayer.ExoPlayer;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class SonoraAudioPlayer_Factory implements Factory<SonoraAudioPlayer> {
  private final Provider<ExoPlayer> exoPlayerProvider;

  public SonoraAudioPlayer_Factory(Provider<ExoPlayer> exoPlayerProvider) {
    this.exoPlayerProvider = exoPlayerProvider;
  }

  @Override
  public SonoraAudioPlayer get() {
    return newInstance(exoPlayerProvider.get());
  }

  public static SonoraAudioPlayer_Factory create(Provider<ExoPlayer> exoPlayerProvider) {
    return new SonoraAudioPlayer_Factory(exoPlayerProvider);
  }

  public static SonoraAudioPlayer newInstance(ExoPlayer exoPlayer) {
    return new SonoraAudioPlayer(exoPlayer);
  }
}
