package com.zemoga.electricars.di

import com.zemoga.electricars.domain.repository.CarRepository
import com.zemoga.electricars.domain.repository.StationRepository
import com.zemoga.electricars.domain.usecase.CarListingUseCase
import com.zemoga.electricars.domain.usecase.CarUseCase
import com.zemoga.electricars.domain.usecase.StationListingUseCase
import com.zemoga.electricars.domain.usecase.StationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideCarListingUseCase(carRepository: CarRepository): CarListingUseCase {
        return CarListingUseCase(carRepository)
    }

    @Provides
    @Singleton
    fun provideCarUseCase(carRepository: CarRepository): CarUseCase {
        return CarUseCase(carRepository)
    }

    @Provides
    @Singleton
    fun providesStationListingUseCase(stationRepository: StationRepository): StationListingUseCase {
        return StationListingUseCase(stationRepository)
    }

    @Provides
    @Singleton
    fun providesStationUseCase(stationRepository: StationRepository): StationUseCase {
        return StationUseCase(stationRepository)
    }
}