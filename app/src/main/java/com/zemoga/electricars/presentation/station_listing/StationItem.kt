package com.zemoga.electricars.presentation.station_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zemoga.electricars.domain.model.station.Station
import com.zemoga.electricars.ui.spacing

@Composable
fun StationItem(modifier: Modifier = Modifier, station: Station, onClick: (String) -> Unit = {}) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(station.id) },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(text = station.name, style = MaterialTheme.typography.h6)
            Text(text = station.address, style = MaterialTheme.typography.body2)
            Row(modifier = Modifier.fillMaxWidth()) {
                if (station.city.isNotEmpty()) {
                    Text(text = "${station.city}, ")
                }
                if (station.state.isNotEmpty()) {
                    Text(text = "${station.state}, ")
                }
                Text(text = station.country)
            }
        }
    }
}

@Preview
@Composable
fun StationItemPreview() {
    StationItem(
        station = Station(
            id = "1",
            name = "Preview Station",
            address = "Address",
            city = "Sahagun",
            state = "Cordoba",
            country = "Colombia"
        )
    )
}
