package com.example.notas.domain.repository

import com.example.notas.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    fun getNoteById(id: Int): Flow<Note>

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)

}