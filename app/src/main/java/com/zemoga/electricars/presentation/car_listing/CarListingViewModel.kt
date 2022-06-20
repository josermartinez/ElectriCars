package com.zemoga.electricars.presentation.car_listing

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.electricars.domain.usecase.CarListingUseCase
import com.zemoga.electricars.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListingViewModel @Inject constructor(
    private val carListingUseCase: CarListingUseCase
) : ViewModel() {

    var state by mutableStateOf(CarListingState())

    init {
        viewModelScope.launch {
            carListingUseCase.invoke().collect {
                when (it) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        it.data?.let { carList ->
                            state = state.copy(carList = carList, isLoading = false)
                        }
                    }
                    is Resource.Error -> {
                        Log.d("CarList", "Error: ${it.message}")
                    }
                }
            }
        }
    }
}