package com.daniil.halushka.todoapp.presentation.screens.elements.details

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme


/**
 * Composable function to display a delete button with an icon and text.
 *
 * @param todoId The ID of the todoItem associated with the delete button.
 * @param onDeleteClick Callback function when the delete button is clicked.
 */
@Composable
fun DetailsDeleteButton(
    todoId: String,
    onDeleteClick: (String) -> Unit
) {
    val redColor = Color.Red

    val activeButtonModifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .clickable(onClick = { onDeleteClick(todoId) })

    Row(
        modifier = activeButtonModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete),
            tint = redColor
        )

        Text(
            text = stringResource(R.string.delete),
            color = redColor,
            modifier = Modifier.padding(start = 16.dp),
            style = AppTheme.typographyScheme.buttonText
        )
    }
}

@Composable
@Preview(name = "Light version", showBackground = true)
fun DetailsDeleteButtonPreview() {
    TodoAppTheme {
        DetailsDeleteButton("") {}
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailsDeleteButtonPreviewDark() {
    DetailsDeleteButton("") {}
}
