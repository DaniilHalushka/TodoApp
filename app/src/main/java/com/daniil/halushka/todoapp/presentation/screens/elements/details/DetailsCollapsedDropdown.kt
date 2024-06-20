package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

@Composable
fun DetailsCollapsedDropdown(
    priority: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /*TODO make click */ }
    ) {
        Text(
            text = stringResource(R.string.priority),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight(500)
        )
        Text(
            text = priority,
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewImportanceDropdown() {
    TodoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            var priority = "No"

            DetailsCollapsedDropdown(
                priority = priority,
            )
        }
    }
}