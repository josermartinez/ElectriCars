package com.zemoga.electricars.presentation.station_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.electricars.domain.usecase.StationUseCase
import com.zemoga.electricars.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenDetailsViewModel @Inject constructor(
    private val stationUseCase: StationUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(StationDetailState())
        private set

    init {
        val stationId = savedStateHandle.get<String>("stationId").orEmpty()
        viewModelScope.launch {
            stationUseCase.invoke(stationId).collect {
                state = when (it) {
                    is Resource.Loading -> {
                        state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state.copy(isLoading = false, station = it.data)
                    }
                    is Resource.Error -> {
                        state.copy(isLoading = false, errorMessage = it.message.orEmpty())
                    }
                }
            }
        }
    }

}