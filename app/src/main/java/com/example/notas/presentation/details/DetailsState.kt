package com.example.notas.presentation.details

import com.example.notas.data.local.entity.Priority
import java.time.LocalDate

data class DetailsState(
    val id: Int? = null,
    val title: String = "",
    val titleHint: String = "Titulo",
    val description: String = "",
    val descriptionHint: String = "Descripcion",
    val createdAt: LocalDate = LocalDate.now(),
    val dueDate: LocalDate = LocalDate.now(),
    val priority: Priority = Priority.LOW,
    val shouldExit: Boolean = false,
    val isEditing: Boolean = false,
    val isDateDialogShowing: Boolean = false
)