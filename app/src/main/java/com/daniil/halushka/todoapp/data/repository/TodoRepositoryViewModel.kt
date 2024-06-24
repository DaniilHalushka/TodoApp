package com.daniil.halushka.todoapp.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.halushka.todoapp.data.models.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    private val listFlow = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>> get() = listFlow

    init {
        loadTodoList()
    }

    private fun loadTodoList() {
        viewModelScope.launch {
            listFlow.value = repository.getTodoList()
        }
    }

    //TODO will be used in future
    fun addTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            repository.addTodoInList(todoItem)
            loadTodoList()
        }
    }

    //TODO will be used in future
    fun updateTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            repository.updateTodo(todoItem)
            loadTodoList()
        }
    }

    //TODO will be used in future
    fun deleteTodoItem(id: String) {
        viewModelScope.launch {
            repository.deleteTodo(id)
            loadTodoList()
        }
    }
}
