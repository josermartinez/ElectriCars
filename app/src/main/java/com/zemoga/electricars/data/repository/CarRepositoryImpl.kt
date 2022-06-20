package com.zemoga.electricars.data.repository

import com.zemoga.electricars.data.remote.source.CarRemoteDataSource
import com.zemoga.electricars.domain.model.Car
import com.zemoga.electricars.domain.model.toCar
import com.zemoga.electricars.domain.repository.CarRepository
import com.zemoga.electricars.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val carRemoteDataSource: CarRemoteDataSource
) : CarRepository {

    override suspend fun getCar(carId: String): Flow<Resource<Car>> {
        return flow {
            runCatching {
                emit(Resource.Loading())
                val response = carRemoteDataSource.getCard(carId)
                val errors = response.errors
                if (errors.isNullOrEmpty().not()) {
                    emit(Resource.Error(errors?.first()?.message.orEmpty()))
                } else {
                    response.data?.let { data ->
                        val car = data.car?.toCar()
                        emit(Resource.Success(car))
                    } ?: emit(Resource.Error(""))
                }
            }
        }
    }

    override suspend fun getCarList(): Flow<Resource<List<Car>>> {
        return flow {
            runCatching {
                emit(Resource.Loading())
                val response = carRemoteDataSource.getCarList()
                val errors = response.errors
                if (errors.isNullOrEmpty().not()) {
                    emit(Resource.Error(errors?.first()?.message.orEmpty()))
                } else {
                    response.data?.let { data ->
                        val car = data.carList?.mapNotNull {
                            it?.toCar()
                        }
                        emit(Resource.Success(car))
                    } ?: emit(Resource.Error(""))
                }
            }.getOrElse {
                emit(Resource.Error(it.message.orEmpty()))
            }
        }
    }
}