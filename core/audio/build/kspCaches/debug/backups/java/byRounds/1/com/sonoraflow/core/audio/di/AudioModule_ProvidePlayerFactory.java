package com.sonoraflow.core.audio.di;

import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AudioModule_ProvidePlayerFactory implements Factory<Player> {
  private final Provider<ExoPlayer> exoPlayerProvider;

  public AudioModule_ProvidePlayerFactory(Provider<ExoPlayer> exoPlayerProvider) {
    this.exoPlayerProvider = exoPlayerProvider;
  }

  @Override
  public Player get() {
    return providePlayer(exoPlayerProvider.get());
  }

  public static AudioModule_ProvidePlayerFactory create(Provider<ExoPlayer> exoPlayerProvider) {
    return new AudioModule_ProvidePlayerFactory(exoPlayerProvider);
  }

  public static Player providePlayer(ExoPlayer exoPlayer) {
    return Preconditions.checkNotNullFromProvides(AudioModule.INSTANCE.providePlayer(exoPlayer));
  }
}
