package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.util.asTime

@Composable
fun DetailsDeadlineText(
    isClicked: Boolean,
    getDeadlineDate: () -> Long?
) {
    Column {
        Text(
            text = stringResource(R.string.date_of_deadline),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )

        if (isClicked) {
            getDeadlineDate()?.let {
                Text(
                    text = it.asTime(),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}