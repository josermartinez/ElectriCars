package com.zemoga.electricars.data.repository

import com.zemoga.electricars.data.remote.source.StationRemoteDataSource
import com.zemoga.electricars.domain.model.station.Station
import com.zemoga.electricars.domain.model.station.toStation
import com.zemoga.electricars.domain.repository.StationRepository
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StationRepositoryImpl @Inject constructor(
    private val stationRemoteDataSource: StationRemoteDataSource
) :
    StationRepository {

    override suspend fun getStationList(): Flow<Resource<List<Station>>> {
        return flow {
            emit(Resource.Loading())
            val response = stationRemoteDataSource.getStationList()
            val errors = response.errors
            if (errors.isNullOrEmpty().not()) {
                emit(Resource.Error(message = errors?.first()?.message.orEmpty()))
            } else {
                response.data?.let { data ->
                    val stationList = data.stationList?.toStation()
                    emit(Resource.Success(stationList))
                } ?: emit(Resource.Error(""))
            }
        }
    }

    override suspend fun getStation(id: String): Flow<Resource<Station>> {
        return flow {
            emit(Resource.Loading())
            val response = stationRemoteDataSource.getStation(id)
            val errors = response.errors
            if (errors.isNullOrEmpty().not()) {
                emit(Resource.Error(message = errors?.first()?.message.orEmpty()))
            } else {
                response.data?.let {
                    val station = it.station?.stationFragment?.toStation()
                    emit(Resource.Success(station))
                } ?: emit(Resource.Error(""))
            }
        }
    }
}