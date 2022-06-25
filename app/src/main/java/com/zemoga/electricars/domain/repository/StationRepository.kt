package com.zemoga.electricars.domain.repository

import com.zemoga.electricars.domain.model.station.Station
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    suspend fun getStationList(): Flow<Resource<List<Station>>>

    suspend fun getStation(id: String): Flow<Resource<Station>>
}