package com.sonoraflow.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sonoraflow.core.database.model.PlaylistEntity
import com.sonoraflow.core.database.model.PlaylistTrackCrossRef
import com.sonoraflow.core.database.model.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlists")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity): Long

    @Delete
    suspend fun deletePlaylist(playlist: PlaylistEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTrackToPlaylist(crossRef: PlaylistTrackCrossRef)

    @Query("DELETE FROM playlist_tracks WHERE playlistId = :playlistId AND trackId = :trackId")
    suspend fun removeTrackFromPlaylist(playlistId: Long, trackId: String)

    // A simpler way to get tracks for a playlist often involves a dedicated Relation data class, 
    // but a query with JOIN is also fine for simple lists.
    @Query("""
        SELECT * FROM tracks 
        INNER JOIN playlist_tracks ON tracks.id = playlist_tracks.trackId 
        WHERE playlist_tracks.playlistId = :playlistId
        ORDER BY playlist_tracks.addedAt ASC
    """)
    fun getTracksForPlaylist(playlistId: Long): Flow<List<TrackEntity>>
}
