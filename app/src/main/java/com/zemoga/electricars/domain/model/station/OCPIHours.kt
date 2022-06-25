package com.zemoga.electricars.domain.model.station

import com.zemoga.electricars.fragment.OCPIHoursFragment
import com.zemoga.electricars.util.orFalse
import com.zemoga.electricars.util.orZero

data class OCPIHours(
    val isTwentyFourSeven: Boolean,
    val regularHours: List<OCPIRegularHours>? = emptyList()
)

fun OCPIHoursFragment.toOCPIHours() = OCPIHours(
    isTwentyFourSeven = this.twentyfourseven.orFalse(),
    regularHours = this.regular_hours?.map {
        OCPIRegularHours(
            weekDay = it?.weekday.orZero(),
            periodBegin = it?.period_begin.orEmpty(),
            periodEnd = it?.period_end.orEmpty()
        )
    }
)
