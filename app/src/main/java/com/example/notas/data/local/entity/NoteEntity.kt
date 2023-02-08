package com.example.notas.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey val id: Int?,
    val title: String,
    val description: String?,
    val priority: Priority,
    val createdAt: Long,
    val dueDate: Long,
    val completed: Boolean = false
)
