package com.daniil.halushka.todoapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.usecase.CountFinishedTodo
import com.daniil.halushka.todoapp.domain.usecase.FinishTodo
import com.daniil.halushka.todoapp.domain.usecase.ReceiveTodoList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val receiveTodoList: ReceiveTodoList,
    private val countFinishedTasks: CountFinishedTodo,
    private val finishTodo: FinishTodo
) : ViewModel() {
    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>> get() = _todoList

    private val _quantityOfFinishedTodo = MutableStateFlow(0)
    val quantityOfFinishedTodo: StateFlow<Int> get() = _quantityOfFinishedTodo

    private val _showFinishedTodo = MutableStateFlow(false)
    val showFinishedTodo: StateFlow<Boolean> get() = _showFinishedTodo

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    init {
        getTodoList()
    }

    private fun getTodoList() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _todoList.value = receiveTodoList.receiveTodoList()
                _quantityOfFinishedTodo.value = countFinishedTasks.countTasks()
            } catch (exception: Exception) {
                _error.value = exception.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun showFinishedTodo(isShowed: Boolean) {
        _showFinishedTodo.value = isShowed
    }

    fun finishTodo(todoId: String, isTodoDone: Boolean) {
        viewModelScope.launch {
            try {
                finishTodo.finishTodo(todoId, isTodoDone)
                getTodoList()
            } catch (exception: Exception) {
                _error.value = exception.message
            }
        }
    }
}
