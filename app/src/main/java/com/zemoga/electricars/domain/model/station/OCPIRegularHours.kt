package com.zemoga.electricars.domain.model.station

data class OCPIRegularHours(
    val weekDay: Int,
    val periodBegin: String,
    val periodEnd: String
)
