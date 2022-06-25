package com.zemoga.electricars.domain.model.station

import com.zemoga.electricars.StationListQuery
import com.zemoga.electricars.fragment.StationFragment

data class Station(
    val id: String,
    val review: ReviewStats? = null,
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val country: String,
    val coordinates: GeoLocation? = null,
    val openingTimes: OCPIHours? = null,
    val images: List<OCPIImage>? = emptyList()
)

fun StationFragment.toStation() = Station(
    id = this.id,
    review = this.review?.reviewFragment?.toReviewStats(),
    name = this.name.orEmpty(),
    address = this.address.orEmpty(),
    city = this.city.orEmpty(),
    state = this.state.orEmpty(),
    country = this.country.orEmpty(),
    coordinates = this.coordinates?.geoLocationFragment?.toGeoLocation(),
    openingTimes = this.opening_times?.oCPIHoursFragment?.toOCPIHours(),
    images = this.images?.mapNotNull {
        it?.oCPIImageFragment?.toOCPIImage()
    }
)

fun List<StationListQuery.StationList?>.toStation() = mapNotNull {
    it?.stationFragment?.toStation()
}
