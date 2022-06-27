package com.zemoga.electricars.presentation.station_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.electricars.domain.usecase.ReviewsUseCase
import com.zemoga.electricars.domain.usecase.StationUseCase
import com.zemoga.electricars.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenDetailsViewModel @Inject constructor(
    private val stationUseCase: StationUseCase,
    private val reviewsUseCase: ReviewsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(StationDetailState())
        private set

    init {
        getStationInfo()
    }

    private fun getStationInfo() {
        val stationId = savedStateHandle.get<String>("stationId").orEmpty()
        viewModelScope.launch {
            stationUseCase.invoke(stationId).collect {
                when (it) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state = state.copy(isLoading = false, station = it.data)
                        getStationReviews(stationId)
                    }
                    is Resource.Error -> {
                        state = state.copy(isLoading = false, errorMessage = it.message.orEmpty())
                    }
                }
            }
        }
    }

    private suspend fun getStationReviews(stationId: String) {
        reviewsUseCase.invoke(stationId).collect {
            when (it) {
                is Resource.Success -> {
                    state = state.copy(reviews = it.data)
                }
                is Resource.Error -> {
                    state = state.copy(errorMessage = it.message.orEmpty())
                }
            }
        }
    }

}