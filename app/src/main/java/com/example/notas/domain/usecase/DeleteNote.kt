package com.example.notas.domain.usecase

import com.example.notas.data.repository.NoteRepositoryImpl
import com.example.notas.domain.models.Note
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val repository: NoteRepositoryImpl
) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }

}