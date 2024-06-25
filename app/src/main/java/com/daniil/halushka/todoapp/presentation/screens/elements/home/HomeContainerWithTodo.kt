package com.daniil.halushka.todoapp.presentation.screens.elements.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.presentation.screens.home.HomeScreenViewModel
import com.daniil.halushka.todoapp.util.asTime

@Composable
fun ContainerWithTodo(
    navigationController: NavController,
    viewModel: HomeScreenViewModel,
) {
    val todoList = viewModel.todoList.collectAsState()
    val showCompleted = viewModel.showFinishedTodo.collectAsState()
    val completedItemsCount = viewModel.quantityOfFinishedTodo.collectAsState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
    ) {
        HomeTopBar(
            completedItemsCount = completedItemsCount.value,
            onEyeIconClick = { showTask -> viewModel.showFinishedTodo(showTask) },
            showCompleted = showCompleted.value
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            items(count = todoList.value.size) { item ->
                val currentItem = todoList.value[item]
                TodoInColumn(
                    todoItem = currentItem,
                    onEditClick = {
                        navigationController.navigate("Details")
                    },
                    onCheckedChange = { todoId, isTodoDone ->
                        viewModel.finishTodo(todoId, isTodoDone)
                    }
                )
            }
        }
    }
}

@Composable
fun TodoInColumn(
    todoItem: TodoItem,
    onEditClick: () -> Unit,
    onCheckedChange: (todoId: String, isTodoDone: Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(todoItem.isDone) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp),
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            CustomCheckbox(
                priority = todoItem.priority,
                isChecked = checked,
                onValueChange = {
                    checked = it
                    onCheckedChange.invoke(todoItem.id, checked)
                },
                modifier = Modifier.padding(end = 8.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = todoItem.text,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground
                )
                todoItem.deadline?.let { deadline ->
                    Text(
                        text = stringResource(id = R.string.deadline_is_in, deadline.asTime()),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
            IconButton(
                onClick = onEditClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(R.string.information_about_task)
                )
            }
        }
    }
}
