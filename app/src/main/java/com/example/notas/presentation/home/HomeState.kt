package com.example.notas.presentation.home

import com.example.notas.domain.NoteType
import com.example.notas.domain.models.Note

data class HomeState(
    val allNotes: List<Note> = emptyList(),
    val pendingNotes: List<Note> = emptyList(),
    val completedNotes: List<Note> = emptyList(),
    val expiredNotes: List<Note> = emptyList(),
    val noteType: NoteType = NoteType.PENDING
)