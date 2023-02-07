package com.example.notas.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notas.domain.usecase.DeleteNote
import com.example.notas.domain.usecase.GetNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotes: GetNotes,
    private val deleteNote: DeleteNote
): ViewModel() {

    private val _state = mutableStateOf(HomeState())

    val state: State<HomeState> =_state

    init {
        viewModelScope.launch {
            getNotes.invoke().collect{ notes ->
                _state.value = state.value.copy(
                    notes = notes
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
        }
    }

}