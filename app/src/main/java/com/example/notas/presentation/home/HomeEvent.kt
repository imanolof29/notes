package com.example.notas.presentation.home

import com.example.notas.domain.models.Note

sealed class HomeEvent {
    data class OnItemClick(val note: Note): HomeEvent()
    data class OnDeleteItem(val note: Note): HomeEvent()
    object OnAddClick: HomeEvent()
}