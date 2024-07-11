package com.daniil.halushka.todoapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val _uniqueTodo = MutableStateFlow<TodoItem?>(null)
    val uniqueTodo: StateFlow<TodoItem?> get() = _uniqueTodo

    fun getUniqueTodo(id: String) {
        viewModelScope.launch {
            val todoItem = repository.getUniqueTodo(id)
            _uniqueTodo.value = todoItem
        }
    }

    fun saveOrUpdateTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            if (todoItem.id.isBlank()) {
                repository.addTodoInList(todoItem)
            } else {
                repository.updateTodoItem(todoItem)
            }
        }
    }


    fun deleteTodo(id: String){
        viewModelScope.launch {
            repository.deleteTodo(id)
        }
    }
}