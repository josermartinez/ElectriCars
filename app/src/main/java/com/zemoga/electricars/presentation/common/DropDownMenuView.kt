package com.zemoga.electricars.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.zemoga.electricars.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenuView(
    modifier: Modifier = Modifier,
    options: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedOptionText by remember {
        mutableStateOf(options[0])
    }

    ExposedDropdownMenuBox(modifier = modifier, expanded = expanded, onExpandedChange = {
        expanded = it
    }) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            value = selectedOptionText,
            onValueChange = {
                selectedOptionText = it
            },
            readOnly = true,
            label = {
                Text(text = stringResource(id = R.string.reviews_section_add_review_rating_hint))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        }) {
            options.forEach { selectedText ->
                DropdownMenuItem(onClick = {
                    selectedOptionText = selectedText
                    expanded = false
                    onItemSelected(selectedText)
                }) {
                    Text(text = selectedText)
                }
            }
        }
    }
}