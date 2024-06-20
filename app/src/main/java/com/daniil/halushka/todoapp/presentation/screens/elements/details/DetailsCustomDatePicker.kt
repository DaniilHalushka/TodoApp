package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsCustomDatePicker() {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = {/* TODO make click*/},
        confirmButton = {
            DialogButtons(
                onCancel = {/* TODO make click*/},
                onDone = { /* TODO make click*/ },
                isDoneEnabled = datePickerState.selectedDateMillis != null
            )
        },
        colors = datePickerDialogColors()
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
            colors = datePickerColors()
        )
    }
}

@Composable
private fun DialogButtons(
    onCancel: () -> Unit,
    onDone: () -> Unit,
    isDoneEnabled: Boolean
) {
    Row(modifier = Modifier.padding(bottom = 16.dp, end = 16.dp)) {
        ActionButton(
            text = stringResource(R.string.cancel),
            onClick = onCancel,
            modifier = Modifier.padding(end = 32.dp)
        )
        ActionButton(
            text = stringResource(R.string.done),
            onClick = onDone,
            enabled = isDoneEnabled
        )
    }
}

@Composable
private fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val color = if (enabled) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }

    Text(
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        modifier = modifier.run {
            if (enabled) clickable(onClick = onClick) else this
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

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TodoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            DetailsCustomDatePicker()
        }
    }
}