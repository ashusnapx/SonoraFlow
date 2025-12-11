package com.sonoraflow.core.data.repository

import com.sonoraflow.core.model.Track
import com.sonoraflow.core.model.ScanProgress
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun getSongs(): Flow<List<Track>>
    fun getScanProgress(): Flow<ScanProgress>
    suspend fun refreshSongs()
}
