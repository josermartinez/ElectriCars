package com.zemoga.electricars.presentation.review

import com.zemoga.electricars.domain.model.review.Review

data class ReviewState(
    val isLoading: Boolean = false,
    val message: String = "",
    val rating: Int = 0,
    val errorMessage: String = "",
    val review: Review? = null,
    val reviewAdded: Boolean = false
)
