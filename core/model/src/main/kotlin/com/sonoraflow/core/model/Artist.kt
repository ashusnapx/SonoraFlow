package com.sonoraflow.core.model

data class Artist(
    val id: String,
    val name: String,
    val albumCount: Int,
    val trackCount: Int,
    val imageUrl: String? = null
)
