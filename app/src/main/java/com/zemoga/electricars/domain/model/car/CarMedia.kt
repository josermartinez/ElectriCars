package com.zemoga.electricars.domain.model.car

import com.zemoga.electricars.CarListQuery
import com.zemoga.electricars.CarQuery

data class CarMedia(
    val image: CarImage?,
    val brand: CarImage?
)

fun CarListQuery.Media.toCarListMedia() = CarMedia(
    image = this.image?.carImageFragment?.toCarImage(),
    brand = this.brand?.carImageFragment?.toCarImage()
)

fun CarQuery.Media.toCarListMedia() = CarMedia(
    image = this.image?.carImageFragment?.toCarImage(),
    brand = this.brand?.carImageFragment?.toCarImage()
)
