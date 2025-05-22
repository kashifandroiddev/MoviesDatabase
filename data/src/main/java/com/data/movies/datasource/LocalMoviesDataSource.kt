package com.data.movies.datasource

import com.data.common.unsupported
import com.data.model.SearchMoviesInput
import com.data.model.SearchMoviesResponse

class LocalMoviesDataSource : MoviesDataSource {

    override suspend fun searchMovies(input: SearchMoviesInput): SearchMoviesResponse {
        unsupported()
    }

}