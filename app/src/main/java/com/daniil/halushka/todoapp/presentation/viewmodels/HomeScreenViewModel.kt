package com.daniil.halushka.todoapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import com.daniil.halushka.todoapp.util.events.EventManager
import com.daniil.halushka.todoapp.util.events.TodoEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the home screen of the TodoApp.
 *
 * @property repository The repository providing access to TodoItem data.
 */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>> get() = _todoList

    private val _quantityOfFinishedTodo = MutableStateFlow(0)
    val quantityOfFinishedTodo: StateFlow<Int> get() = _quantityOfFinishedTodo

    private val _showFinishedTodo = MutableStateFlow(false)
    val showFinishedTodo: StateFlow<Boolean> get() = _showFinishedTodo

    init {
        getTodoList()
        observeEvents()
    }

    /**
     * Retrieves the list of todoItems from the repository and updates [_todoList] and [_quantityOfFinishedTodo].
     */
    fun getTodoList() {
        viewModelScope.launch {
            _todoList.value = repository.getTodoList()
            _quantityOfFinishedTodo.value = repository.countFinishedTodo()
        }
    }

    /**
     * Sets the visibility of completed todoItems based on [isShowed].
     *
     * @param isShowed Boolean flag indicating whether to show completed todoItems.
     */
    fun showFinishedTodo(isShowed: Boolean) {
        _showFinishedTodo.value = isShowed
    }

    /**
     * Marks a todoItem as finished or unfinished and updates the todoList accordingly.
     *
     * @param todoId The ID of the todoItem to mark as finished or unfinished.
     * @param isTodoDone Boolean flag indicating whether the todoItem is marked as finished.
     */
    fun finishTodo(todoId: String, isTodoDone: Boolean) {
        viewModelScope.launch {
            repository.finishTodo(todoId, isTodoDone)
            getTodoList()
        }
    }

    /**
     * Observes events related to todoItems using [EventManager].
     * Refreshes the todoList when a [TodoEvent.TodoListUpdated] event occurs.
     */
    private fun observeEvents() {
        viewModelScope.launch {
            EventManager.events.collect { event ->
                when (event) {
                    is TodoEvent.TodoListUpdated -> getTodoList()
                }
            }
        }
    }
}
