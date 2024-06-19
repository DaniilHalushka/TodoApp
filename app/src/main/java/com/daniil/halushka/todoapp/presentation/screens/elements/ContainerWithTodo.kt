package com.daniil.halushka.todoapp.presentation.screens.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoRepository
import com.daniil.halushka.todoapp.util.asTime

@Composable
fun ContainerWithTodo(repository: TodoRepository, onEditItem: (TodoItem) -> Unit) {
    val itemsInContainer by remember { mutableStateOf(repository.getTodoList()) }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(itemsInContainer) { item ->
            TodoInColumn(
                todoItem = item,
                onEditClick = { onEditItem(item) }
            )
        }
    }
}

@Composable
fun TodoInColumn(
    todoItem: TodoItem,
    onEditClick: () -> Unit
) {
    var checked by remember { mutableStateOf(todoItem.isDone) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            CustomCheckbox(
                priority = todoItem.priority,
                isChecked = checked,
                onValueChange = { checked = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = todoItem.text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                todoItem.deadline?.let { deadline ->
                    Text(
                        text = stringResource(id = R.string.deadline_is_in, deadline.asTime()),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ContainerWithTodo(repository = TodoRepository()) {
    }
}
