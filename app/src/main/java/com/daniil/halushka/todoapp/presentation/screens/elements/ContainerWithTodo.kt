package com.daniil.halushka.todoapp.presentation.screens.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.data.models.TodoItem

class ContainerWithTodo {
}

@Composable
fun TodoInGrid(
    todoItem: TodoItem,
    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onEditClick)
    ) {
        Box {

        }
        CustomCheckbox(
            isChecked = todoItem.isDone,
            onCheckedChange = { task -> onCheckedChange(task) },
            priority = todoItem.priority
        )
        Column {
            Text(
                text = todoItem.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = stringResource(R.string.todo_priority, todoItem.priority))
            todoItem.deadline?.let { deadline ->
                Text(text = "Deadline is in: $deadline")
            }
        }
    }
}