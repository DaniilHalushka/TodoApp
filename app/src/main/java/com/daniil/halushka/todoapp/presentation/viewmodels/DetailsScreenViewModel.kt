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
 * ViewModel for managing the details screen of a TodoItem.
 *
 * @property repository The repository providing access to TodoItem data.
 */
@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _uniqueTodo = MutableStateFlow<TodoItem?>(null)
    val uniqueTodo: StateFlow<TodoItem?> get() = _uniqueTodo

    /**
     * Retrieves a unique TodoItem from the repository based on its [id] and updates [_uniqueTodo].
     *
     * @param id The ID of the TodoItem to retrieve.
     */
    fun getUniqueTodo(id: String) {
        viewModelScope.launch {
            val todoItem = repository.getUniqueTodo(id)
            _uniqueTodo.value = todoItem
        }
    }

    /**
     * Saves or updates the provided [todoItem] in the repository.
     * Sends a [TodoEvent.TodoListUpdated] event after the operation.
     *
     * @param todoItem The TodoItem to save or update.
     */
    fun saveOrUpdateTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            if (todoItem.id.isBlank()) {
                repository.addTodoInList(todoItem)
            } else {
                repository.updateTodoItem(todoItem)
            }
            EventManager.sendEvent(TodoEvent.TodoListUpdated)
        }
    }

    /**
     * Deletes the TodoItem with the specified [id] from the repository.
     * Sends a [TodoEvent.TodoListUpdated] event after the operation.
     *
     * @param id The ID of the TodoItem to delete.
     */
    fun deleteTodo(id: String) {
        viewModelScope.launch {
            repository.deleteTodo(id)
            EventManager.sendEvent(TodoEvent.TodoListUpdated)
        }
    }
}
