package com.example.notas.domain.models

import com.example.notas.data.local.entity.Priority
import java.time.LocalDateTime

data class Note(
    val id: Int? = null,
    val title: String,
    val description: String?,
    val priority: Priority,
    val createdAt: LocalDateTime,
    val dueDate: LocalDateTime,
    val completed: Boolean = false
)