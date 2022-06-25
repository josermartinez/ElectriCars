package com.zemoga.electricars.presentation.station_listing

import com.zemoga.electricars.domain.model.station.Station

data class StationListingState(
    val isLoading: Boolean = false,
    val stationList: List<Station> = emptyList(),
    val errorMessage: String = ""
)
