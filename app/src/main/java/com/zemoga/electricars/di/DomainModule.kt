package com.zemoga.electricars.di

import com.zemoga.electricars.domain.repository.CarRepository
import com.zemoga.electricars.domain.repository.ReviewRepository
import com.zemoga.electricars.domain.repository.StationRepository
import com.zemoga.electricars.domain.usecase.*
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

    @Provides
    @Singleton
    fun providesReviewsUseCase(reviewRepository: ReviewRepository): ReviewsUseCase {
        return ReviewsUseCase(reviewRepository)
    }

    @Provides
    @Singleton
    fun providesAddReviewUseCase(reviewRepository: ReviewRepository): AddReviewUseCase {
        return AddReviewUseCase(reviewRepository)
    }
}