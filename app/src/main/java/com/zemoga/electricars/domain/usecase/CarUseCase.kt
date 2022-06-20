package com.zemoga.electricars.domain.usecase

import com.zemoga.electricars.domain.model.Car
import com.zemoga.electricars.domain.repository.CarRepository
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow

class CarUseCase(private val carRepository: CarRepository) {

    suspend operator fun invoke(carId: String): Flow<Resource<Car>> {
        return carRepository.getCar(carId)
    }
}