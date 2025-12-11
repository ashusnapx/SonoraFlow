package com.sonoraflow.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey val id: String, // MediaStore ID
    val title: String,
    val artistName: String,
    val albumName: String,
    val durationMs: Long,
    val contentUri: String,
    val albumArtUri: String?,
    val isFavorite: Boolean = false,
    val lastPlayedTimestamp: Long = 0L
)
