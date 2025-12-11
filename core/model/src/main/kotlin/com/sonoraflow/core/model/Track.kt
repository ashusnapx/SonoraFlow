package com.sonoraflow.core.model

data class Track(
    val id: String,
    val title: String,
    val artistId: String,
    val artistName: String,
    val albumId: String,
    val albumName: String,
    val durationMs: Long,
    val contentUri: String,
    val albumArtUri: String?,
    val trackNumber: Int,
    val year: Int,
    val isFavorite: Boolean = false
)
