package com.zemoga.electricars.presentation.station_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.electricars.domain.usecase.StationListingUseCase
import com.zemoga.electricars.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationListingViewModel @Inject constructor(
    private val stationListingUseCase: StationListingUseCase
) : ViewModel() {

    var stationListState by mutableStateOf(StationListingState())
        private set

    init {
        viewModelScope.launch {
            stationListingUseCase.invoke().collect {
                when (it) {
                    is Resource.Loading -> {
                        stationListState = stationListState.copy(isLoading = true)
                    }
                    is Resource.Error -> {
                        stationListState = stationListState.copy(
                            isLoading = false,
                            errorMessage = it.message.orEmpty()
                        )
                    }
                    is Resource.Success -> {
                        it.data?.let { stationList ->
                            stationListState = stationListState.copy(
                                isLoading = false,
                                stationList = stationList,
                                errorMessage = ""
                            )
                        }
                    }
                }
            }
        }
    }
}