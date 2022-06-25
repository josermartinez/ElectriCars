package com.zemoga.electricars.domain.repository

import com.zemoga.electricars.domain.model.car.Car
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    suspend fun getCar(carId: String): Flow<Resource<Car>>

    suspend fun getCarList(): Flow<Resource<List<Car>>>
}