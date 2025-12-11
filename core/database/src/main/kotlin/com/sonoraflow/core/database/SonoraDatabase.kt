package com.sonoraflow.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sonoraflow.core.database.dao.PlaylistDao
import com.sonoraflow.core.database.dao.TrackDao
import com.sonoraflow.core.database.model.PlaylistEntity
import com.sonoraflow.core.database.model.PlaylistTrackCrossRef
import com.sonoraflow.core.database.model.TrackEntity

@Database(
    entities = [
        TrackEntity::class,
        PlaylistEntity::class,
        PlaylistTrackCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SonoraDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
    abstract fun playlistDao(): PlaylistDao
}
