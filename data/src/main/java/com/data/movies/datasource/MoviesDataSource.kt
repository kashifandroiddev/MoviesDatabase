package com.data.movies.datasource

import com.data.model.SearchMoviesInput
import com.data.model.SearchMoviesResponse

interface MoviesDataSource {

    suspend fun searchMovies(input: SearchMoviesInput): SearchMoviesResponse

}