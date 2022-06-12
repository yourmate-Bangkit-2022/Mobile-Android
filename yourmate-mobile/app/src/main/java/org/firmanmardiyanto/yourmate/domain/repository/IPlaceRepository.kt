package org.firmanmardiyanto.yourmate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.Place

interface IPlaceRepository {
    fun getPlaces(): Flow<Resource<List<Place>>>
}