package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R

@Composable
fun DetailsTextField(
    text: String,
    onTextChange: (String) -> Unit
) {

    val customSelectedColor = TextSelectionColors(
        handleColor = MaterialTheme.colorScheme.tertiary,
        backgroundColor = Color.LightGray
    )
    CompositionLocalProvider(value = LocalTextSelectionColors provides customSelectedColor) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .heightIn(min = 128.dp, max = 256.dp),
            value = text,
            onValueChange = { value -> onTextChange(value) },
            placeholder = { TextFieldPlaceholder() },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                selectionColors = customSelectedColor,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.tertiary,
                disabledIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,

                )
        )
    }
}

@Composable
fun TextFieldPlaceholder() {
    Text(
        text = stringResource(R.string.what_you_need_to_do),
        color = MaterialTheme.colorScheme.onTertiary,
        fontSize = 16.sp
    )
}