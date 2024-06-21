package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailsDeadlineSwitch(
    isClicked: Boolean,
    getDeadlineDate: () -> Long?,
    onCheckedChange: (Boolean) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        DetailsDeadlineText(
            isClicked = isClicked,
            getDeadlineDate = getDeadlineDate

        )

        DetailsCustomSwitch(
            checked = isClicked,
            onCheckedChange = onCheckedChange
        )
    }
}