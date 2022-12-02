package com.example.notas.domain.usecase

import com.example.notas.data.repository.NoteRepositoryImpl
import javax.inject.Inject

class GetNotes @Inject constructor(
    private val repositoryImpl: NoteRepositoryImpl
) {

    operator fun invoke() = repositoryImpl.getNotes()

}