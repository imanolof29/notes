package com.example.notas.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notas.domain.models.Note
import com.example.notas.domain.usecase.GetNoteById
import com.example.notas.domain.usecase.InsertNote
import com.example.notas.domain.usecase.UpdateNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val insertNote: InsertNote,
    private val getNoteById: GetNoteById,
    private val updateNote: UpdateNote
): ViewModel() {

    private val _state = mutableStateOf(DetailsState())

    val state: State<DetailsState> = _state

    init {
        savedStateHandle.get<String>("noteId")?.let { noteId ->
            _state.value = state.value.copy(
                id = noteId.toInt()
            )
            if(noteId.toInt() != -1){
                viewModelScope.launch {
                    getNoteById.invoke(state.value.id!!).collect{
                        _state.value = state.value.copy(
                            title = it.title,
                            description = it.description ?: ""
                        )
                    }
                }
            }
        }

    }

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.OnSave -> {
                viewModelScope.launch {
                    insertNote.invoke(
                        Note(
                            title = state.value.title,
                            description = state.value.description,
                            createdAt = state.value.createdAt.atStartOfDay(),
                            dueDate = state.value.dueDate.atStartOfDay(),
                            priority = state.value.priority
                        )
                    )
                }
                _state.value = state.value.copy(shouldExit = true)
            }
            is DetailsEvent.OnUpdate -> {
                viewModelScope.launch {
                    updateNote.invoke(
                        Note(
                            id = state.value.id,
                            title = state.value.title,
                            description = state.value.description,
                            createdAt = state.value.createdAt.atStartOfDay(),
                            dueDate = state.value.dueDate.atStartOfDay(),
                            priority = state.value.priority
                        )
                    )
                }
                _state.value = state.value.copy(shouldExit = true)
            }
            is DetailsEvent.OnTitleChange -> _state.value = state.value.copy(title = event.value)
            is DetailsEvent.OnDescriptionChange -> _state.value = state.value.copy(description = event.value)
            is DetailsEvent.OnBackPressed -> _state.value = state.value.copy(shouldExit = true)
            is DetailsEvent.OnDateClick -> _state.value = state.value.copy(isDateDialogShowing = true)
        }
    }

}