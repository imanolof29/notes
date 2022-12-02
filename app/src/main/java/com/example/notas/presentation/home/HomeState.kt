package com.example.notas.presentation.home

import com.example.notas.domain.models.Note

data class HomeState(
    val notes: List<Note> = emptyList()
)