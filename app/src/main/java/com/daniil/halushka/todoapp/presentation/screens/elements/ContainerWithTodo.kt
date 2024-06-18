package com.daniil.halushka.todoapp.presentation.screens.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoRepository

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
//TODO                onCheckedChange = { isChecked ->
//                    repository.updateTodo(
//                        item.copy(
//                            isDone = isChecked,
//                            changeDate = System.currentTimeMillis()
//                        )
//                    )
//                },
                onEditClick = { onEditItem(item) }
            )
        }
    }
}

@Composable
fun TodoInColumn(
    todoItem: TodoItem,
//TODO    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit
) {
    var checked by remember { mutableStateOf(todoItem.isDone) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onEditClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomCheckbox(
            isChecked = checked,
            onValueChange = { checked = it },
            modifier = Modifier.padding(12.dp)
        )
        Column {
            Text(
                text = todoItem.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = stringResource(R.string.todo_priority, todoItem.priority))
            todoItem.deadline?.let { deadline ->
                Text(text = stringResource(R.string.deadline_is_in, deadline))
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