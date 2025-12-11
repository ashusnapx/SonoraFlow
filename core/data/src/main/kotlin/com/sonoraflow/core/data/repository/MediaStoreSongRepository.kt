package com.sonoraflow.core.data.repository

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.sonoraflow.core.model.Track
import com.sonoraflow.core.model.ScanProgress
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaStoreSongRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : SongRepository {

    private val _songs = MutableStateFlow<List<Track>>(emptyList())
    private val _scanProgress = MutableStateFlow(ScanProgress())
    private val TAG = "MediaStoreSongRepo"

    override fun getSongs(): Flow<List<Track>> = _songs.asStateFlow()
    override fun getScanProgress(): Flow<ScanProgress> = _scanProgress.asStateFlow()

    override suspend fun refreshSongs() = withContext(Dispatchers.IO) {
        try {
            _scanProgress.value = ScanProgress(isScanning = true, filesFound = 0, currentFile = "Starting scan...")
            delay(500) // Let UI show "Starting" state
            
            Log.d(TAG, "===========================================")
            Log.d(TAG, "=== MEDIASTORE SCAN STARTING ===")
            Log.d(TAG, "Android Version: ${Build.VERSION.SDK_INT} (${Build.VERSION.RELEASE})")
            Log.d(TAG, "===========================================")
            
            val tracks = mutableListOf<Track>()
            
            // Use the correct URI based on Android version
            val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }
            
            Log.d(TAG, "Collection URI: $collection")

            // Minimal projection
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA
            )

            // Use DURATION > 0 instead of IS_MUSIC (which is buggy)
            val selection = "${MediaStore.Audio.Media.DURATION} > ?"
            val selectionArgs = arrayOf("0")
            val sortOrder = "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"

            Log.d(TAG, "Querying MediaStore...")
            Log.d(TAG, "Selection: $selection")
            
            val cursor = context.contentResolver.query(
                collection,
                projection,
                selection,
                selectionArgs,
                sortOrder
            )
            
            if (cursor == null) {
                Log.e(TAG, "!!! CURSOR IS NULL !!!")
                Log.e(TAG, "This means permission is denied or MediaStore is unavailable")
                _scanProgress.value = ScanProgress(
                    isScanning = false,
                    filesFound = 0,
                    error = "Permission denied or MediaStore unavailable"
                )
                _songs.value = emptyList()
                return@withContext
            }
            
            val totalCount = cursor.count
            Log.d(TAG, "=========================================")
            Log.d(TAG, "CURSOR COUNT: $totalCount files found!")
            Log.d(TAG, "=========================================")
            
            cursor.use { c ->
                val idColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val albumColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
                val durationColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val dataColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

                var count = 0
                while (c.moveToNext()) {
                    count++
                    try {
                        val id = c.getLong(idColumn)
                        val title = c.getString(titleColumn) ?: "Unknown Track"
                        val artist = c.getString(artistColumn) ?: "Unknown Artist"
                        val album = c.getString(albumColumn) ?: "Unknown Album"
                        val duration = c.getLong(durationColumn)
                        val filePath = c.getString(dataColumn) ?: ""

                        // Log EVERY file for complete transparency
                        Log.d(TAG, "[$count/$totalCount] $title by $artist")
                        Log.d(TAG, "   └─ Path: $filePath")
                        Log.d(TAG, "   └─ Duration: ${duration}ms")

                        // Update progress every file
                        _scanProgress.value = ScanProgress(
                            isScanning = true,
                            filesFound = count,
                            currentFile = "$title - $artist"
                        )

                        val contentUri = ContentUris.withAppendedId(collection, id).toString()
                        
                        // Try to get album art
                        val albumArtUri = try {
                            ContentUris.withAppendedId(
                                Uri.parse("content://media/external/audio/albumart"),
                                id
                            ).toString()
                        } catch (e: Exception) {
                            null
                        }

                        tracks.add(
                            Track(
                                id = id.toString(),
                                title = title,
                                artistId = "0",
                                artistName = artist,
                                albumId = "0",
                                albumName = album,
                                durationMs = duration,
                                contentUri = contentUri,
                                albumArtUri = albumArtUri,
                                trackNumber = 0,
                                year = 0
                            )
                        )
                    } catch (e: Exception) {
                        Log.e(TAG, "Error processing track at position $count", e)
                    }
                }
            }
            
            Log.d(TAG, "===========================================")
            Log.d(TAG, "=== SCAN COMPLETE ===")
            Log.d(TAG, "Total tracks found: ${tracks.size}")
            Log.d(TAG, "===========================================")
            
            _songs.value = tracks
            _scanProgress.value = ScanProgress(
                isScanning = false,
                filesFound = tracks.size,
                currentFile = if (tracks.isNotEmpty()) "Scan complete!" else "No files found"
            )
            
        } catch (e: SecurityException) {
            Log.e(TAG, "!!! SECURITY EXCEPTION !!!", e)
            Log.e(TAG, "Permission was denied!")
            _scanProgress.value = ScanProgress(
                isScanning = false,
                filesFound = 0,
                error = "Permission denied: ${e.message}"
            )
            _songs.value = emptyList()
        } catch (e: Exception) {
            Log.e(TAG, "!!! UNEXPECTED ERROR !!!", e)
            _scanProgress.value = ScanProgress(
                isScanning = false,
                filesFound = 0,
                error = "Error: ${e.message}"
            )
            _songs.value = emptyList()
        }
    }
}
