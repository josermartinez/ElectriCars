package com.zemoga.electricars.domain.usecase

import com.zemoga.electricars.domain.model.station.Station
import com.zemoga.electricars.domain.repository.StationRepository
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow

class StationUseCase(private val stationRepository: StationRepository) {

    suspend operator fun invoke(id: String): Flow<Resource<Station>> {
        return stationRepository.getStation(id)
    }
}