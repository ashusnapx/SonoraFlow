package com.sonoraflow.core.ai.engine

import com.sonoraflow.core.model.Track
import kotlinx.coroutines.flow.Flow

interface RecommendationEngine {
    /**
     * Returns a list of recommended tracks based on context.
     */
    suspend fun getRecommendations(
        history: List<Track>,
        currentMood: String? = null
    ): List<Track>

    /**
     * Explains why a track was recommended.
     */
    fun getExplanation(trackId: String): String
}
