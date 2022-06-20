package com.zemoga.electricars.presentation.car_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.zemoga.electricars.R
import com.zemoga.electricars.domain.model.Car
import com.zemoga.electricars.ui.spacing

@Composable
fun CarDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: CarDetailsViewModel = hiltViewModel(),
    carId: String = ""
) {
    val state = viewModel.state
    if (state.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(model = state.carDetails?.media?.image?.url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color.Cyan)
            )
            InformationCard(
                modifier = Modifier.offset(y = -MaterialTheme.spacing.large),
                car = state.carDetails
            )
        }
    }
}

@Composable
fun InformationCard(modifier: Modifier = Modifier, car: Car?) {
    val version = car?.naming?.version.orEmpty()
    val edition = car?.naming?.edition.orEmpty()
    val charge = car?.naming?.chargeTripVersion.orEmpty()
    Card(
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp))
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = car?.media?.brand?.thumbnailUrl),
                contentDescription = null,
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
            )
            Text(
                text = stringResource(id = R.string.car_make_label, car?.naming?.make.orEmpty()),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Divider(modifier = Modifier.fillMaxWidth())
            Text(
                text = stringResource(R.string.car_model_label, car?.naming?.model.orEmpty()),
                textAlign = TextAlign.Center
            )
            if (version.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.car_version_label, version),
                    textAlign = TextAlign.Center
                )
            }
            if (edition.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.car_edition_label, edition),
                    textAlign = TextAlign.Center
                )
            }
            if (charge.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.car_charge_trip_label, charge),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
