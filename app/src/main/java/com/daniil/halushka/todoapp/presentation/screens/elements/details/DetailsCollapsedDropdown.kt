package com.daniil.halushka.todoapp.presentation.screens.elements.details

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

@Composable
fun DetailsCollapsedDropdown(
    priority: String,
    isClicked: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { isClicked(true) }
    ) {
        Text(
            text = stringResource(R.string.priority),
            color = AppTheme.colorScheme.labelPrimaryColor,
            style = AppTheme.typographyScheme.title
        )
        Text(
            text = priority,
            color = AppTheme.colorScheme.labelTertiaryColor,
            style = AppTheme.typographyScheme.bodyText
        )
    }
}

@Composable
@Preview(name = "Light version", showBackground = true)
fun DetailsCollapsedDropdownPreview() {
    TodoAppTheme {
        DetailsCollapsedDropdown(
            priority = Priority.USUAL_PRIORITY,
            isClicked = {}
        )
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailsCollapsedDropdownDark() {
    TodoAppTheme {
        DetailsCollapsedDropdown(
            priority = Priority.USUAL_PRIORITY,
            isClicked = {}
        )
    }
}