package com.daniil.halushka.todoapp.presentation.screens.elements.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

/**
 * Composable function to display a custom TextField with specific styling and behavior.
 *
 * @param text Initial text to display in the TextField.
 * @param onTextChange Callback invoked when the text in the TextField changes.
 */
@Composable
fun DetailsTextField(
    text: String,
    onTextChange: (String) -> Unit
) {

    val customSelectedColor = TextSelectionColors(
        handleColor = AppTheme.colorScheme.blueColor,
        backgroundColor = Color.LightGray
    )
    CompositionLocalProvider(value = LocalTextSelectionColors provides customSelectedColor) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .heightIn(min = 128.dp, max = 256.dp),
            value = text,
            textStyle = AppTheme.typographyScheme.bodyText,
            onValueChange = { value -> onTextChange(value) },
            placeholder = { TextFieldPlaceholder() },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                selectionColors = customSelectedColor,
                focusedTextColor = AppTheme.colorScheme.labelPrimaryColor,
                cursorColor = AppTheme.colorScheme.blueColor,
                disabledIndicatorColor = AppTheme.colorScheme.backPrimaryColor,
                focusedIndicatorColor = AppTheme.colorScheme.blueColor,
                unfocusedIndicatorColor = AppTheme.colorScheme.backPrimaryColor,
                focusedContainerColor = AppTheme.colorScheme.backSecondaryColor,
                unfocusedContainerColor = AppTheme.colorScheme.backSecondaryColor,
                )
        )
    }
}

@Composable
fun TextFieldPlaceholder() {
    Text(
        text = stringResource(R.string.what_you_need_to_do),
        color = AppTheme.colorScheme.labelTertiaryColor,
        fontSize = 16.sp
    )
}

@Composable
@Preview(name = "Light version", showBackground = true)
fun DetailsTextFieldPreview() {
    TodoAppTheme {
        var text by remember { mutableStateOf( "") }
        DetailsTextField(
            text = "Text text to preview",
            onTextChange = { newText -> text = newText }
        )
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailsTextFieldDark() {
    var text by remember { mutableStateOf( "") }
    DetailsTextField(
        text = "Text text to preview",
        onTextChange = { newText -> text = newText }
    )
}