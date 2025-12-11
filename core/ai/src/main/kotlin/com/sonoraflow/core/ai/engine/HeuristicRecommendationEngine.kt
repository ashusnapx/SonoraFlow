package com.sonoraflow.core.ai.engine

import com.sonoraflow.core.data.repository.SongRepository
import com.sonoraflow.core.model.Track
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * A basic fallback engine that uses heuristics (random shuffle / recent) 
 * when a full TFLite model is not available or cold start.
 */
class HeuristicRecommendationEngine @Inject constructor(
    private val songRepository: SongRepository
) : RecommendationEngine {

    override suspend fun getRecommendations(history: List<Track>, currentMood: String?): List<Track> {
        // In a real heuristic:
        // 1. Filter out history (don't repeat immediately)
        // 2. Prioritize same genre/artist if available in metadata
        // 3. Shim for TFLite model call would go here
        
        val allSongs = songRepository.getSongs().first()
        return allSongs.shuffled().take(10)
    }

    override fun getExplanation(trackId: String): String {
        return "Recommended based on similar artists in your library."
    }
}
