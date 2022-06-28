package com.zemoga.electricars.presentation.review

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.electricars.domain.usecase.AddReviewUseCase
import com.zemoga.electricars.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val addReviewUseCase: AddReviewUseCase
) : ViewModel() {

    var reviewState by mutableStateOf(ReviewState())
        private set

    fun onMessageChanged(message: String) {
        reviewState = reviewState.copy(message = message)
    }

    fun onAddReviewClicked(stationId: String, rating: Int) {
        viewModelScope.launch {
            addReviewUseCase.invoke(stationId, rating, reviewState.message)
                .collect {
                    reviewState = when (it) {
                        is Resource.Loading -> {
                            reviewState.copy(
                                isLoading = true,
                                reviewAdded = false
                            )
                        }
                        is Resource.Error -> {
                            reviewState.copy(
                                errorMessage = it.message.orEmpty(),
                                isLoading = false,
                                reviewAdded = false
                            )
                        }
                        is Resource.Success -> {
                            reviewState.copy(
                                isLoading = false,
                                review = it.data,
                                reviewAdded = true,
                                message = "",
                                rating = 0
                            )
                        }
                    }
                }
        }
    }
}