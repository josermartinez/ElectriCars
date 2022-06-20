package com.zemoga.electricars.presentation.car_listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zemoga.electricars.ElectriCarScreens
import com.zemoga.electricars.ui.spacing

@Composable
fun CarListScreen(
    modifier: Modifier = Modifier,
    viewModel: CarListingViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {
            items(items = state.carList, key = { it.id }) {
                CarItem(item = it, onClick = { carId ->
                    navController.navigate("${ElectriCarScreens.CAR_DETAILS.name}/$carId")
                })
            }
        }
    }
}