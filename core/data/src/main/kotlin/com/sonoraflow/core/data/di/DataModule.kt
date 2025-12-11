package com.sonoraflow.core.data.di

import com.sonoraflow.core.data.repository.MediaStoreSongRepository
import com.sonoraflow.core.data.repository.SongRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindSongRepository(
        impl: MediaStoreSongRepository
    ): SongRepository
}
