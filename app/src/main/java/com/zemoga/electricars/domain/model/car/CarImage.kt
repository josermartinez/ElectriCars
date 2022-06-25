package com.zemoga.electricars.domain.model.car

import com.zemoga.electricars.fragment.CarImageFragment

data class CarImage(
    val id: String,
    val url: String,
    val height: Int,
    val width: Int,
    val thumbnailUrl: String,
    val thumbnailHeight: Int,
    val thumbnailWidth: Int
)

fun CarImageFragment.toCarImage() = CarImage(
    id = this.id.orEmpty(),
    url = this.url.orEmpty(),
    height = this.height ?: 0,
    width = this.width ?: 0,
    thumbnailUrl = this.thumbnail_url.orEmpty(),
    thumbnailHeight = this.thumbnail_height ?: 0,
    thumbnailWidth = this.thumbnail_width ?: 0
)
