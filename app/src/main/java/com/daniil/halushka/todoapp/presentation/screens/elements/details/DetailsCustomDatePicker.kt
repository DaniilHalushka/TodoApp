package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsCustomDatePicker(
    cancelChoice: () -> Unit,
    confirmChoice: (Long?) -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = cancelChoice,
        confirmButton = {
            DialogButtons(
                cancelChoice = cancelChoice,
                confirmChoice = { confirmChoice(datePickerState.selectedDateMillis) },
                datePickerState = datePickerState
            )
        },
        colors = datePickerDialogColors()
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = true,
            colors = datePickerColors()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogButtons(
    cancelChoice: () -> Unit,
    confirmChoice: (Long?) -> Unit,
    datePickerState: DatePickerState
) {
    Row(modifier = Modifier.padding(bottom = 16.dp, end = 16.dp)) {
        ActionButton(
            text = stringResource(R.string.cancel),
            onClick = cancelChoice,
            modifier = Modifier.padding(end = 32.dp)
        )
        ActionButton(
            text = stringResource(R.string.done),
            onClick = {
                confirmChoice(datePickerState.selectedDateMillis)
            },
            isClicked = datePickerState.selectedDateMillis != null
        )
    }
}

@Composable
private fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isClicked: Boolean = true
) {
    val color = if (isClicked) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }

    Text(
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        modifier = modifier.run {
            if (isClicked) clickable(onClick = onClick) else this
        },
        color = color
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun datePickerDialogColors() = DatePickerDefaults.colors(
    containerColor = MaterialTheme.colorScheme.secondary,
    selectedDayContainerColor = MaterialTheme.colorScheme.surface
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun datePickerColors() = DatePickerDefaults.colors(
    containerColor = MaterialTheme.colorScheme.secondary,
    selectedYearContainerColor = MaterialTheme.colorScheme.tertiary,
    selectedDayContainerColor = MaterialTheme.colorScheme.tertiary,
    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.tertiary,
    disabledSelectedDayContainerColor = MaterialTheme.colorScheme.tertiary,
    todayContentColor = MaterialTheme.colorScheme.onPrimary,
    todayDateBorderColor = MaterialTheme.colorScheme.tertiary
)