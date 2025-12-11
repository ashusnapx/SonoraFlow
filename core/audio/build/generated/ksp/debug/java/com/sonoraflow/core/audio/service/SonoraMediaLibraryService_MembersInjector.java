package com.sonoraflow.core.audio.service;

import androidx.media3.common.Player;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SonoraMediaLibraryService_MembersInjector implements MembersInjector<SonoraMediaLibraryService> {
  private final Provider<Player> playerProvider;

  public SonoraMediaLibraryService_MembersInjector(Provider<Player> playerProvider) {
    this.playerProvider = playerProvider;
  }

  public static MembersInjector<SonoraMediaLibraryService> create(Provider<Player> playerProvider) {
    return new SonoraMediaLibraryService_MembersInjector(playerProvider);
  }

  @Override
  public void injectMembers(SonoraMediaLibraryService instance) {
    injectPlayer(instance, playerProvider.get());
  }

  @InjectedFieldSignature("com.sonoraflow.core.audio.service.SonoraMediaLibraryService.player")
  public static void injectPlayer(SonoraMediaLibraryService instance, Player player) {
    instance.player = player;
  }
}
