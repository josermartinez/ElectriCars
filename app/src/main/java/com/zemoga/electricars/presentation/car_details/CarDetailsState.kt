package com.zemoga.electricars.presentation.car_details

import com.zemoga.electricars.domain.model.Car

data class CarDetailsState(
    val carDetails: Car? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
