package com.example.notas.data.mapper

import com.example.notas.data.local.entity.NoteEntity
import com.example.notas.domain.models.Note
import java.time.ZoneId
import java.time.LocalDateTime
import java.time.Instant

fun NoteEntity.toDomain(): Note {
    return Note(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        createdAt = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(this.createdAt),
            ZoneId.systemDefault()
        ),
        dueDate = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(this.dueDate),
            ZoneId.systemDefault()
        ),
        completed = this.completed
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        createdAt = this.createdAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        dueDate = this.dueDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        completed = this.completed
    )
}