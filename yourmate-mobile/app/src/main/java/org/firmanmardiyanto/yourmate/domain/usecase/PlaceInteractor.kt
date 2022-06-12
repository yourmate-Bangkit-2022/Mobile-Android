package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.data.repository.PlaceRepository
import org.firmanmardiyanto.yourmate.domain.model.Place
import javax.inject.Inject

class PlaceInteractor @Inject constructor(private val placeRepository: PlaceRepository) :
    PlaceUseCase {
    override fun getPlaces(): Flow<Resource<List<Place>>> = placeRepository.getPlaces()
}