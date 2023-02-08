package com.example.notas.presentation.details

import com.example.notas.domain.models.Note
import java.time.LocalDateTime

sealed class DetailsEvent {
    object OnBackPressed: DetailsEvent()
    object OnSave: DetailsEvent()
    object OnUpdate: DetailsEvent()
    object OnDateClick: DetailsEvent()
    data class OnDateUpdate(val newDate: LocalDateTime): DetailsEvent()
    data class OnTitleChange(val value: String): DetailsEvent()
    data class OnDescriptionChange(val value: String): DetailsEvent()
}