package com.zemoga.electricars.presentation.car_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.electricars.domain.usecase.CarUseCase
import com.zemoga.electricars.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarDetailsViewModel @Inject constructor(
    private val carUseCase: CarUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(CarDetailsState())

    init {
        getCarDetails(savedStateHandle.get<String>("carId").orEmpty())
    }

    private fun getCarDetails(carId: String) {
        viewModelScope.launch {
            carUseCase.invoke(carId).collect {
                state = when (it) {
                    is Resource.Loading -> {
                        state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state.copy(isLoading = false, carDetails = it.data)
                    }
                    is Resource.Error -> {
                        state.copy(isLoading = false, errorMessage = it.message.orEmpty())
                    }
                }
            }
        }
    }
}