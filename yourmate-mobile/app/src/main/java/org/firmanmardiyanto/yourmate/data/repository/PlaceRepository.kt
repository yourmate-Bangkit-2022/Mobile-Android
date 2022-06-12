package org.firmanmardiyanto.yourmate.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.data.api.YourmateApi
import org.firmanmardiyanto.yourmate.domain.model.Place
import org.firmanmardiyanto.yourmate.domain.repository.IPlaceRepository
import javax.inject.Inject

private const val TAG = "PlaceRepository"

class PlaceRepository @Inject constructor(
    private val yourmateApi: YourmateApi
) : IPlaceRepository {
    override fun getPlaces(): Flow<Resource<List<Place>>> = flow {
        emit(Resource.Loading())
        try {
            val response = yourmateApi.getPlaces()
            if (response.isSuccessful) {
                val places = response.body()?.data?.map {
                    Place(
                        id = it.id.toInt(),
                        title = it.attributes.title,
                        createdAt = it.attributes.createdAt,
                        imageUrl = it.attributes.imageUrl,
                        location = it.attributes.location,
                        desc = it.attributes.desc,
                        rating = it.attributes.rating,
                        category = it.attributes.category,
                    )
                }
                if (places != null) {
                    emit(Resource.Success(places))
                } else {
                    emit(Resource.Error("No data"))
                }
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

}