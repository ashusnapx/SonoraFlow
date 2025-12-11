package com.sonoraflow.core.data.repository

import com.sonoraflow.core.model.Track
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun getSongs(): Flow<List<Track>>
    suspend fun refreshSongs()
}
