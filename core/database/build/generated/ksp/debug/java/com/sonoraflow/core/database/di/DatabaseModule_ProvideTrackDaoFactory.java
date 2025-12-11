package com.sonoraflow.core.database.di;

import com.sonoraflow.core.database.SonoraDatabase;
import com.sonoraflow.core.database.dao.TrackDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideTrackDaoFactory implements Factory<TrackDao> {
  private final Provider<SonoraDatabase> databaseProvider;

  public DatabaseModule_ProvideTrackDaoFactory(Provider<SonoraDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TrackDao get() {
    return provideTrackDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideTrackDaoFactory create(
      Provider<SonoraDatabase> databaseProvider) {
    return new DatabaseModule_ProvideTrackDaoFactory(databaseProvider);
  }

  public static TrackDao provideTrackDao(SonoraDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTrackDao(database));
  }
}
