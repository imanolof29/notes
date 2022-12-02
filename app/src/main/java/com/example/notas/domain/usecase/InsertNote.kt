package com.example.notas.domain.usecase

import com.example.notas.data.repository.NoteRepositoryImpl
import com.example.notas.domain.models.Note
import javax.inject.Inject

class InsertNote @Inject constructor(
    private val noteRepository: NoteRepositoryImpl
) {

    suspend operator fun invoke(note: Note){
        noteRepository.insertNote(note)
    }

}