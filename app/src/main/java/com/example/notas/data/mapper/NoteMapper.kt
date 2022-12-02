package com.example.notas.data.mapper

import com.example.notas.data.local.entity.NoteEntity
import com.example.notas.domain.models.Note

fun NoteEntity.toDomain(): Note {
    return Note(
        id = this.id,
        title = this.title,
        description = this.description
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = null,
        title = this.title,
        description = this.description
    )
}