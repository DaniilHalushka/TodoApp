package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DetailsDeadlineBlock(
    getDeadlineDate: () -> Long?,
    onDateSelect: (Long?) -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }

    DetailsDeadlineSwitch(
        isClicked = isClicked,
        onCheckedChange = { isClicked = it },
        getDeadlineDate = getDeadlineDate
    )

    if (isClicked) {
        DetailsCustomDatePicker(
            cancelChoice = { isClicked = false },
            confirmChoice = { date ->
                onDateSelect(date)
                isClicked = false
            }
        )
    }
}

