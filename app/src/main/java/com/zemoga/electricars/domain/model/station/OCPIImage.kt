package com.zemoga.electricars.domain.model.station

import com.zemoga.electricars.fragment.OCPIImageFragment

data class OCPIImage(
    val url: String,
    val thumbnail: String
)

fun OCPIImageFragment.toOCPIImage() = OCPIImage(
    url = this.url.orEmpty(),
    thumbnail = this.thumbnail.orEmpty()
)
