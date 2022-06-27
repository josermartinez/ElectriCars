package com.zemoga.electricars.di

import com.zemoga.electricars.data.repository.CarRepositoryImpl
import com.zemoga.electricars.data.repository.ReviewRepositoryImpl
import com.zemoga.electricars.data.repository.StationRepositoryImpl
import com.zemoga.electricars.domain.repository.CarRepository
import com.zemoga.electricars.domain.repository.ReviewRepository
import com.zemoga.electricars.domain.repository.StationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCarRepository(
        carRepositoryImpl: CarRepositoryImpl
    ): CarRepository

    @Binds
    @Singleton
    abstract fun bindStationRepository(
        stationRepositoryImpl: StationRepositoryImpl
    ): StationRepository

    @Binds
    @Singleton
    abstract fun bindReviewRepository(
        reviewRepositoryImpl: ReviewRepositoryImpl
    ): ReviewRepository
}