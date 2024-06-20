package com.daniil.halushka.todoapp.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoRepository
import com.daniil.halushka.todoapp.presentation.screens.elements.home.ContainerWithTodo
import com.daniil.halushka.todoapp.presentation.screens.elements.home.CustomFAB
import com.daniil.halushka.todoapp.presentation.screens.elements.home.CustomTopBar
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

@Composable
fun HomeScreen(repository: TodoRepository, onEditItem: (TodoItem) -> Unit) {
    val todoItems = repository.getTodoList()
    val completedTodo = todoItems.count { task -> task.isDone }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CustomTopBar(
                completedItemsCount = completedTodo
            )
            ContainerWithTodo(
                repository = repository,
                onEditItem = onEditItem
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            CustomFAB(
                onClick = { /*TODO*/ },
            )
        }
    }
}


//TODO delete preview
@Preview(showBackground = true)
@Composable
fun Preview1() {
    TodoAppTheme {
        HomeScreen(repository = TodoRepository()) {

        }
    }
}