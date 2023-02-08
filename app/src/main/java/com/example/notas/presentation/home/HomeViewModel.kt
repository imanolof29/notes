package com.example.notas.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notas.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPendingNotes: GetPendingNotes,
    private val getCompletedNotes: GetCompletedNotes,
    private val getExpiredNotes: GetExpiredNotes,
    private val deleteNote: DeleteNote
): ViewModel() {

    private val _state = mutableStateOf(HomeState())

    val state: State<HomeState> =_state

    init {
        viewModelScope.launch {
            getExpiredNotes.invoke().collect{ expiredNotes ->
                _state.value = state.value.copy(
                    expiredNotes = expiredNotes
                )
            }
        }
        viewModelScope.launch {
            getPendingNotes.invoke().collect { pendingNotes ->
                _state.value = state.value.copy(
                    pendingNotes = pendingNotes
                )
            }
        }
        viewModelScope.launch {
            getCompletedNotes.invoke().collect { completedNotes ->
                _state.value = state.value.copy(
                    completedNotes = completedNotes
                )
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.OnAddClick -> {

            }
            is HomeEvent.OnDeleteItem -> {
                viewModelScope.launch {
                    deleteNote.invoke(event.note)
                }
            }
            is HomeEvent.OnNoteTypeChange -> {
                _state.value = state.value.copy(noteType = event.noteType)
            }
        }
    }

}