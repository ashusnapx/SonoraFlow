package com.sonoraflow.core.model

data class Album(
    val id: String,
    val title: String,
    val artistId: String,
    val artistName: String,
    val songCount: Int,
    val albumArtUri: String?,
    val year: Int
)
