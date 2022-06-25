package com.zemoga.electricars.domain.model.car

import com.zemoga.electricars.CarListQuery
import com.zemoga.electricars.CarQuery

data class Car(
    val id: String,
    val naming: CarNaming?,
    val media: CarMedia?
)

fun CarListQuery.CarList.toCar() = Car(
    id = this.id.orEmpty(),
    naming = this.naming?.carListNamingFragment?.toCartListNaming(),
    media = this.media?.toCarListMedia()
)

fun List<CarListQuery.CarList>.toCar() = this.map {
    it.toCar()
}

fun CarQuery.Car.toCar() = Car(
    id = this.id.orEmpty(),
    naming = this.naming?.carNamingFragment?.toCartListNaming(),
    media = this.media?.toCarListMedia()
)
