package com.zemoga.electricars.domain.usecase

import com.zemoga.electricars.domain.model.Car
import com.zemoga.electricars.domain.repository.CarRepository
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow

class CarListingUseCase(private val carRepository: CarRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Car>>> {
        return carRepository.getCarList()
    }
}