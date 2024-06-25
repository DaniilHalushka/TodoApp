package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.util.asTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsDeadlineBlock(
    getDeadlineDate: () -> Long?,
    onDateSelect: (Long?) -> Unit
) {
    var isDeadlineActive by remember { mutableStateOf(getDeadlineDate() != null) }
    var date by remember { mutableStateOf(getDeadlineDate()) }
    var dateDialogController by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = date)

    DeadlineRow(
        isDeadlineActive = isDeadlineActive,
        date = date,
        onDateClick = { if (isDeadlineActive) dateDialogController = true },
        onSwitchChange = { changeHappened ->
            isDeadlineActive = changeHappened
            if (changeHappened) {
                dateDialogController = true
            } else {
                onDateSelect(null)
                date = null
            }
        }
    )

    if (dateDialogController) {
        DeadlineDatePickerDialog(
            datePickerState = datePickerState,
            onDismiss = { dateDialogController = false },
            onConfirm = {
                dateDialogController = false
                date = datePickerState.selectedDateMillis
                if (date != null) {
                    onDateSelect(date)
                }
            }
        )
    }
}

@Composable
private fun DeadlineRow(
    isDeadlineActive: Boolean,
    date: Long?,
    onDateClick: () -> Unit,
    onSwitchChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.clickable(onClick = onDateClick),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.date_of_deadline),
                color = MaterialTheme.colorScheme.onBackground
            )
            AnimatedVisibility(visible = isDeadlineActive) {
                date?.let { date ->
                    Text(
                        text = date.asTime(),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
        Switch(
            checked = isDeadlineActive,
            onCheckedChange = onSwitchChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.secondary,
                checkedTrackColor = MaterialTheme.colorScheme.tertiary,
                uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeadlineDatePickerDialog(
    datePickerState: DatePickerState,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    DatePickerDialog(
        colors = datePickerDialogColors(),
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = stringResource(R.string.done),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            colors = datePickerColors()
        )
    }
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
