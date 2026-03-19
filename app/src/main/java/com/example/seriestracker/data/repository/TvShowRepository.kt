package com.example.seriestracker.data.repository

import com.example.seriestracker.data.remote.EpisodateApiService
import com.example.seriestracker.model.TvShow
import com.example.seriestracker.model.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(
    private val apiService: EpisodateApiService
) {
    suspend fun getPopularShows(page: Int = 1): List<TvShow> {
        // On appelle l'API, on convertit chaque DTO en modèle métier
        return apiService.getPopularShows(page).tvShows.map { it.toDomain() }
    }
}