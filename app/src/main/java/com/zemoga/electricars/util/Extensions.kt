package com.zemoga.electricars.util


fun Double?.orZero() = this ?: 0.0

fun Int?.orZero() = this ?: 0

fun Boolean?.orFalse() = this ?: false

