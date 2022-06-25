package com.zemoga.electricars.domain.model.station

import com.zemoga.electricars.fragment.GeoLocationFragment

data class GeoLocation(
    val latitude: String,
    val longitude: String
)

fun GeoLocationFragment.toGeoLocation() = GeoLocation(
    latitude = this.latitude.orEmpty(),
    longitude = this.longitude.orEmpty()
)
