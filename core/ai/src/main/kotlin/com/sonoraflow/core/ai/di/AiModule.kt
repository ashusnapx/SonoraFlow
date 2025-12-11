package com.sonoraflow.core.ai.di

import com.sonoraflow.core.ai.engine.HeuristicRecommendationEngine
import com.sonoraflow.core.ai.engine.RecommendationEngine
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AiModule {

    @Binds
    @Singleton
    abstract fun bindRecommendationEngine(
        impl: HeuristicRecommendationEngine
    ): RecommendationEngine
}
