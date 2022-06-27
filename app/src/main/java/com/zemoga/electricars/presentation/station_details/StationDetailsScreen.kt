package com.zemoga.electricars.presentation.station_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.zemoga.electricars.R
import com.zemoga.electricars.domain.model.station.Station
import com.zemoga.electricars.ui.spacing
import com.zemoga.electricars.util.orZero
import java.util.*

@Composable
fun StationDetailsScreen(
    modifier: Modifier = Modifier,
    stationId: String = "",
    viewModel: ScreenDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val stationCoordinates = LatLng(
            state.station?.coordinates?.latitude?.toDouble().orZero(),
            state.station?.coordinates?.longitude?.toDouble().orZero()
        )
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(stationCoordinates, 5f)
        }
        val mapUiSettings by remember {
            mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
        }

        Column(modifier = modifier) {
            GoogleMap(
                modifier = Modifier.height(200.dp),
                cameraPositionState = cameraPositionState,
                uiSettings = mapUiSettings
            ) {
                Marker(
                    state = MarkerState(position = stationCoordinates),
                    title = state.station?.name.orEmpty()
                )
            }
            StationDetailItem(
                modifier = Modifier
                    .offset(y = -MaterialTheme.spacing.large)
                    .padding(MaterialTheme.spacing.medium),
                station = state.station
            )
        }
    }
}

@Composable
fun StationDetailItem(modifier: Modifier = Modifier, station: Station? = null) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = stringResource(id = R.string.station_details_item_title),
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (station?.name.isNullOrEmpty().not()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.station_details_station_name_label),
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = station?.name.orEmpty(),
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.station_details_station_address_label),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )
                Column(modifier = Modifier.weight(2f)) {
                    Text(text = station?.address.orEmpty(), style = MaterialTheme.typography.body2)
                    Row() {
                        if (station?.city.isNullOrEmpty().not()) {
                            Text(
                                text = "${station?.city.orEmpty()}, ",
                                style = MaterialTheme.typography.body2
                            )
                        }
                        if (station?.state.isNullOrEmpty().not()) {
                            Text(
                                text = "${station?.state.orEmpty()}, ",
                                style = MaterialTheme.typography.body2
                            )
                        }
                        if (station?.country.isNullOrEmpty().not()) {
                            Text(
                                text = "${station?.country.orEmpty()}, ",
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            if (station?.openingTimes?.regularHours.isNullOrEmpty().not()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.station_details_station_opening_times_label),
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.weight(1f)
                    )
                    Column(modifier = Modifier.weight(2f)) {
                        station?.openingTimes?.regularHours?.forEach {
                            if (it.periodBegin.isNotEmpty() && it.periodEnd.isNotEmpty()) {
                                OpeningTimeItem(
                                    weekDay = it.weekDay,
                                    openingTime = it.periodBegin,
                                    closingTime = it.periodEnd
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OpeningTimeItem(
    modifier: Modifier = Modifier,
    weekDay: Int,
    openingTime: String,
    closingTime: String
) {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, weekDay)
    }
    val day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    Text(text = "$day   $openingTime - $closingTime", style = MaterialTheme.typography.body2)
}

@Preview
@Composable
fun StationDetailItemPreview() {
    StationDetailItem()
}
