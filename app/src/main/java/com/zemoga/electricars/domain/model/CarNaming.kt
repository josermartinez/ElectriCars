package com.zemoga.electricars.domain.model

import com.zemoga.electricars.fragment.CarListNamingFragment
import com.zemoga.electricars.fragment.CarNamingFragment

data class CarNaming(
    val make: String,
    val model: String,
    val version: String,
    val edition: String,
    val chargeTripVersion: String
)

fun CarListNamingFragment.toCartListNaming() = CarNaming(
    make = this.make.orEmpty(),
    model = this.model.orEmpty(),
    version = this.version.orEmpty(),
    edition = this.edition.orEmpty(),
    chargeTripVersion = this.chargetrip_version.orEmpty()
)

fun CarNamingFragment.toCartListNaming() = CarNaming(
    make = this.make.orEmpty(),
    model = this.model.orEmpty(),
    version = this.version.orEmpty(),
    edition = this.edition.orEmpty(),
    chargeTripVersion = this.chargetrip_version.orEmpty()
)
