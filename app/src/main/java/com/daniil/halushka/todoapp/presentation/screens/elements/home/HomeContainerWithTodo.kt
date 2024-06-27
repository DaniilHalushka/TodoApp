package com.daniil.halushka.todoapp.presentation.screens.elements.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoRepository
import com.daniil.halushka.todoapp.domain.usecases.CountFinishedTodo
import com.daniil.halushka.todoapp.domain.usecases.FinishTodo
import com.daniil.halushka.todoapp.domain.usecases.ReceiveTodoList
import com.daniil.halushka.todoapp.presentation.screens.home.HomeScreenViewModel
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme
import com.daniil.halushka.todoapp.util.asTime

@Composable
fun ContainerWithTodo(
    navigationController: NavController,
    viewModel: HomeScreenViewModel,
    listState: LazyListState,
    toolbarHeight: Dp
) {
    val todoList by viewModel.todoList.collectAsState()
    val showFinished by viewModel.showFinishedTodo.collectAsState()
    val completedItemsCount by viewModel.quantityOfFinishedTodo.collectAsState()

    Column(
        modifier = Modifier
            .background(AppTheme.colorScheme.backPrimaryColor)
    ) {
        HomeTopBar(
            completedItemsCount = completedItemsCount,
            onEyeIconClick = { showTask -> viewModel.showFinishedTodo(showTask) },
            showFinished = showFinished,
            height = toolbarHeight
        )
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(count = todoList.size) { index ->
                val currentItem = todoList[index]
                TodoInColumn(
                    todoItem = currentItem,
                    onEditClick = {
                        navigationController.navigate("Details")
                    },
                    showFinishedTodo = showFinished,
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
    showFinishedTodo: Boolean,
    onCheckedChange: (todoId: String, isTodoDone: Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(todoItem.isDone) }
    val textStyle = if (checked) {
        AppTheme.typographyScheme.bodyText.copy(textDecoration = TextDecoration.LineThrough)
    } else AppTheme.typographyScheme.bodyText


    AnimatedVisibility(
        visible = !(!showFinishedTodo && checked),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut(animationSpec = tween(durationMillis = 400)) + shrinkVertically()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 2.dp),
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 2.dp,
        ) {
            Box(
                modifier = Modifier
                    .background(AppTheme.colorScheme.backSecondaryColor)
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (todoItem.priority != Priority.USUAL_PRIORITY && !checked) {
                            val (iconRes, tintColor) = when (todoItem.priority) {
                                Priority.LOW_PRIORITY -> R.drawable.ic_low_priority to AppTheme.colorScheme.lightGrayColor
                                else -> R.drawable.ic_urgent_priority to AppTheme.colorScheme.redColor
                            }

                            Icon(
                                painter = painterResource(id = iconRes),
                                tint = tintColor,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = todoItem.text,
                                color = AppTheme.colorScheme.labelPrimaryColor,
                                style = textStyle,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                            )
                            todoItem.deadline?.let { deadline ->
                                Text(
                                    text = stringResource(
                                        id = R.string.deadline_is_in,
                                        deadline.asTime()
                                    ),
                                    color = AppTheme.colorScheme.labelTertiaryColor,
                                    style = textStyle,
                                )
                            }
                        }
                        IconButton(
                            onClick = onEditClick,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = stringResource(R.string.information_about_task),
                                tint = AppTheme.colorScheme.labelTertiaryColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "Light version", showBackground = true)
fun ContainerWithTodoPreview() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        val fakeViewModel = HomeScreenViewModel(
            ReceiveTodoList(TodoRepository()),
            CountFinishedTodo(TodoRepository()),
            FinishTodo(TodoRepository())
        )
        val listState = rememberLazyListState()

        var toolbarHeight by remember { mutableStateOf(0.dp) }

        LaunchedEffect(listState) {
            snapshotFlow { listState.firstVisibleItemScrollOffset }
                .collect { scrollOffset ->
                    toolbarHeight = if (scrollOffset > 0) 64.dp else 100.dp
                }
        }

        val animatedHeight by animateDpAsState(
            targetValue = toolbarHeight, label = stringResource(id = R.string.toolbar)
        )

        ContainerWithTodo(
            navigationController = navigationController,
            viewModel = fakeViewModel,
            listState = listState,
            toolbarHeight = animatedHeight
        )
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ContainerWithTodoPreviewDark() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        val fakeViewModel = HomeScreenViewModel(
            ReceiveTodoList(TodoRepository()),
            CountFinishedTodo(TodoRepository()),
            FinishTodo(TodoRepository())
        )
        val listState = rememberLazyListState()

        var toolbarHeight by remember { mutableStateOf(0.dp) }

        LaunchedEffect(listState) {
            snapshotFlow { listState.firstVisibleItemScrollOffset }
                .collect { scrollOffset ->
                    toolbarHeight = if (scrollOffset > 0) 64.dp else 100.dp
                }
        }

        val animatedHeight by animateDpAsState(
            targetValue = toolbarHeight, label = stringResource(id = R.string.toolbar)
        )

        ContainerWithTodo(
            navigationController = navigationController,
            viewModel = fakeViewModel,
            listState = listState,
            toolbarHeight = animatedHeight
        )
    }
}