package com.zemoga.electricars.presentation.station_details

import com.zemoga.electricars.domain.model.review.Review
import com.zemoga.electricars.domain.model.station.Station

data class StationDetailState(
    val isLoading: Boolean = false,
    val station: Station? = null,
    val reviews: List<Review>? = emptyList(),
    val errorMessage: String = ""
)
