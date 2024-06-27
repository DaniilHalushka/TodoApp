package com.daniil.halushka.todoapp.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.data.repository.TodoRepository
import com.daniil.halushka.todoapp.domain.usecases.CountFinishedTodo
import com.daniil.halushka.todoapp.domain.usecases.FinishTodo
import com.daniil.halushka.todoapp.domain.usecases.ReceiveTodoList
import com.daniil.halushka.todoapp.presentation.screens.elements.home.ContainerWithTodo
import com.daniil.halushka.todoapp.presentation.screens.elements.home.CustomFAB
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

@Composable
fun HomeScreen(
    navigationController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            ContainerWithTodo(
                navigationController = navigationController,
                viewModel = viewModel,
                listState = listState,
                toolbarHeight = animatedHeight
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues()),
            contentAlignment = Alignment.BottomEnd
        ) {
            CustomFAB(
                navigationController = navigationController
            )
        }
    }
}


@Composable
@Preview(name = "Light version", showBackground = true)
fun HomeScreenPreview() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        val fakeViewModel = HomeScreenViewModel(
            ReceiveTodoList(TodoRepository()),
            CountFinishedTodo(TodoRepository()),
            FinishTodo(TodoRepository())
        )
        HomeScreen(navigationController = navigationController, viewModel = fakeViewModel)
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreviewDark() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        val fakeViewModel = HomeScreenViewModel(
            ReceiveTodoList(TodoRepository()),
            CountFinishedTodo(TodoRepository()),
            FinishTodo(TodoRepository())
        )
        HomeScreen(navigationController = navigationController, viewModel = fakeViewModel)
    }
}