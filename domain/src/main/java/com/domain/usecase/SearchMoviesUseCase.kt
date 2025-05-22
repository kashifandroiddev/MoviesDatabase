package com.domain.usecase

import com.domain.model.Media
import com.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(query: String, maxPageThresholds: Int): Flow<List<Media>> =
        movieRepository.searchMovies(query, maxPageThresholds)

}