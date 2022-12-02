package com.example.notas.domain.usecase

import com.example.notas.data.repository.NoteRepositoryImpl
import javax.inject.Inject

class GetNoteById @Inject constructor(
    private val homeRepository: NoteRepositoryImpl
) {

    operator fun invoke(id: Int) = homeRepository.getNoteById(id)

}