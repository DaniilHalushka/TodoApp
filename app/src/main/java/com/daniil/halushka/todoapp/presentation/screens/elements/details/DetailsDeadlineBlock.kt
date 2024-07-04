package com.daniil.halushka.todoapp.presentation.screens.elements.details

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme
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

    LaunchedEffect(getDeadlineDate()) {
        isDeadlineActive = getDeadlineDate() != null
        date = getDeadlineDate()
    }

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
                color = AppTheme.colorScheme.labelPrimaryColor,
                style = AppTheme.typographyScheme.bodyText
            )
            AnimatedVisibility(visible = isDeadlineActive) {
                date?.let { date ->
                    Text(
                        text = date.asTime(),
                        color = AppTheme.colorScheme.blueColor,
                        style = AppTheme.typographyScheme.bodyText
                    )
                }
            }
        }
        Switch(
            checked = isDeadlineActive,
            onCheckedChange = onSwitchChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AppTheme.colorScheme.backSecondaryColor,
                checkedTrackColor = AppTheme.colorScheme.blueColor,
                uncheckedThumbColor = AppTheme.colorScheme.whiteColor,
                uncheckedTrackColor = AppTheme.colorScheme.lightGrayColor
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
    val currentDate = System.currentTimeMillis()
    var showInvalidDateError by remember { mutableStateOf(false) }

    DatePickerDialog(
        colors = datePickerDialogColors(),
        onDismissRequest = {
            showInvalidDateError = false
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = {
                val selectedDate = datePickerState.selectedDateMillis
                if (selectedDate != null && selectedDate >= currentDate) {
                    showInvalidDateError = false
                    onConfirm()
                } else {
                    showInvalidDateError = true
                }
            }) {
                Text(
                    text = stringResource(R.string.done),
                    color = AppTheme.colorScheme.blueColor,
                    style = AppTheme.typographyScheme.buttonText
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                showInvalidDateError = false
                onDismiss()
            }) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = AppTheme.colorScheme.blueColor,
                    style = AppTheme.typographyScheme.buttonText
                )
            }
        }
    ) {
        Column {
            DatePicker(
                state = datePickerState,
                colors = datePickerColors()
            )
            if (showInvalidDateError) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.invalid_date_error),
                    color = AppTheme.colorScheme.redColor,
                    style = AppTheme.typographyScheme.bodyText,
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun datePickerDialogColors() = DatePickerDefaults.colors(
    containerColor = AppTheme.colorScheme.backSecondaryColor,
    selectedDayContainerColor = AppTheme.colorScheme.labelPrimaryColor
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun datePickerColors() = DatePickerDefaults.colors(
    headlineContentColor = AppTheme.colorScheme.blueColor,
    containerColor = AppTheme.colorScheme.backSecondaryColor,
    currentYearContentColor = AppTheme.colorScheme.greenColor,
    selectedYearContainerColor = AppTheme.colorScheme.blueColor,
    selectedDayContainerColor = AppTheme.colorScheme.blueColor,
    dayContentColor = AppTheme.colorScheme.labelPrimaryColor,
    dayInSelectionRangeContainerColor = AppTheme.colorScheme.blueColor,
    disabledSelectedDayContainerColor = AppTheme.colorScheme.lightGrayColor,
    titleContentColor = AppTheme.colorScheme.labelPrimaryColor,
    todayContentColor = AppTheme.colorScheme.redColor,
    todayDateBorderColor = AppTheme.colorScheme.redColor,
    weekdayContentColor = AppTheme.colorScheme.lightGrayColor,
    navigationContentColor = AppTheme.colorScheme.labelPrimaryColor
)

@Composable
@Preview(name = "Light version", showBackground = true)
fun DetailsDeadlineBlockPreview() {
    TodoAppTheme {
        Column(
            modifier = Modifier
                .background(AppTheme.colorScheme.backPrimaryColor)
        ) {
            DetailsDeadlineBlock(
                getDeadlineDate = { System.currentTimeMillis() },
                onDateSelect = {}
            )
        }
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailsDeadlineBlockDark() {
    TodoAppTheme {
        Column(
            modifier = Modifier
                .background(AppTheme.colorScheme.backPrimaryColor)
        ) {
            DetailsDeadlineBlock(
                getDeadlineDate = { System.currentTimeMillis() },
                onDateSelect = {}
            )
        }
    }
}