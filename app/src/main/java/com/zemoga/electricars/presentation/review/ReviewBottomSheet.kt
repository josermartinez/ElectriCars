package com.zemoga.electricars.presentation.review

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zemoga.electricars.R
import com.zemoga.electricars.presentation.common.DropDownMenuView
import com.zemoga.electricars.ui.spacing

@Composable
fun ReviewBottomSheet(
    modifier: Modifier = Modifier,
    stationId: String,
    reviewViewModel: ReviewViewModel = hiltViewModel(),
    onReviewAdded: () -> Unit
) {
    val state = reviewViewModel.reviewState
    var selectedOption by rememberSaveable {
        mutableStateOf("1")
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 300.dp)
            .padding(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Divider(
            modifier = Modifier
                .width(20.dp)
                .height(4.dp)
        )

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minHeight = 300.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.small))
            Text(
                text = stringResource(id = R.string.reviews_section_add_review_label),
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.small))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))
            DropDownMenuView(
                modifier = Modifier.fillMaxWidth(),
                options = listOf("1", "2", "3", "4", "5"),
                onItemSelected = { option ->
                    selectedOption = option
                })
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.message,
                onValueChange = {
                    reviewViewModel.onMessageChanged(it)
                },
                label = {
                    Text(text = stringResource(id = R.string.reviews_section_add_review_placeholder_hint))
                },
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = {
                    reviewViewModel.onAddReviewClicked(
                        stationId = stationId,
                        rating = selectedOption.toInt()
                    )
                }) {
                    Text(text = stringResource(id = R.string.reviews_section_add_review_button_label))
                }
            }
        }

        if (state.reviewAdded) {
            Toast.makeText(
                LocalContext.current,
                stringResource(id = R.string.reviews_section_review_added_success_message),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}