package com.zemoga.electricars.presentation.car_listing

import com.zemoga.electricars.domain.model.Car

data class CarListingState(
    val carList: List<Car> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
