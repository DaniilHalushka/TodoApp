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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.data.repository.TodoRepository
import com.daniil.halushka.todoapp.domain.usecases.home.CountFinishedTodo
import com.daniil.halushka.todoapp.domain.usecases.home.FinishTodo
import com.daniil.halushka.todoapp.domain.usecases.home.ReceiveTodoList
import com.daniil.halushka.todoapp.presentation.screens.elements.home.ContainerWithTodo
import com.daniil.halushka.todoapp.presentation.screens.elements.home.CustomFAB
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

@Composable
fun HomeScreen(
    navigationController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    val toolbarHeight by rememberToolbarHeight(listState)

    val nestedScrollConnection = createNestedScrollConnection(listState)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Column {
            ContainerWithTodo(
                navigationController = navigationController,
                viewModel = viewModel,
                listState = listState,
                toolbarHeight = toolbarHeight
            )
        }
        BottomEndFAB(navigationController)
    }
}

@Composable
fun rememberToolbarHeight(listState: LazyListState): State<Dp> {
    var toolbarHeight by remember { mutableStateOf(100.dp) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { scrollOffset ->
                toolbarHeight = if (scrollOffset > 0) 64.dp else 100.dp
            }
    }

    return animateDpAsState(
        targetValue = toolbarHeight,
        label = stringResource(id = R.string.toolbar)
    )
}

fun createNestedScrollConnection(listState: LazyListState): NestedScrollConnection {
    return object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            return if (available.y > 0 && listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) {
                Offset.Zero
            } else {
                Offset(available.x, 0f)
            }
        }

        override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
            return if (available.y < 0 && listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) {
                Offset.Zero
            } else {
                Offset(available.x, 0f)
            }
        }
    }
}

@Composable
fun BottomEndFAB(navigationController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        contentAlignment = Alignment.BottomEnd
    ) {
        CustomFAB(navigationController = navigationController)
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