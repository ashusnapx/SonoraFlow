package com.sonoraflow.core.database.di

import android.content.Context
import androidx.room.Room
import com.sonoraflow.core.database.SonoraDatabase
import com.sonoraflow.core.database.dao.PlaylistDao
import com.sonoraflow.core.database.dao.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSonoraDatabase(
        @ApplicationContext context: Context
    ): SonoraDatabase = Room.databaseBuilder(
        context,
        SonoraDatabase::class.java,
        "sonoraflow.db"
    ).build()

    @Provides
    fun provideTrackDao(database: SonoraDatabase): TrackDao = database.trackDao()

    @Provides
    fun providePlaylistDao(database: SonoraDatabase): PlaylistDao = database.playlistDao()
}
