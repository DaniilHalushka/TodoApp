package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DetailsDeadlineSwitch(
    checked: Boolean,
    getDeadlineDate: () -> Long?,
    onCheckedChange: (Boolean) -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        DetailsDeadlineText(
            isClicked = checked,
            getDeadlineDate = getDeadlineDate

        )

        DetailsCustomSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}