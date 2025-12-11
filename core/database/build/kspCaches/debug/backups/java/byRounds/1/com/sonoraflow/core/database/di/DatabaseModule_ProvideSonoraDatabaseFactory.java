package com.sonoraflow.core.database.di;

import android.content.Context;
import com.sonoraflow.core.database.SonoraDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideSonoraDatabaseFactory implements Factory<SonoraDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideSonoraDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SonoraDatabase get() {
    return provideSonoraDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideSonoraDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideSonoraDatabaseFactory(contextProvider);
  }

  public static SonoraDatabase provideSonoraDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSonoraDatabase(context));
  }
}
