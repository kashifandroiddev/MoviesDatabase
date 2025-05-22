package com.data.mapper

import com.domain.model.Media
import com.data.model.Result
import com.data.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieMapper {

    // Converting server models to domain models . from here the domain models will used.
    // will become flexible if server changes anything we don,t have to change ahead of our domain layer
    fun map(media: Flow<List<Result>>): Flow<List<Media>> =
        media.map { item -> item.map { media -> media.toDomain() } }

}