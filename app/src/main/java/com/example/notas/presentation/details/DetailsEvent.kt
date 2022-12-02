package com.example.notas.presentation.details

sealed class DetailsEvent {
    object OnBackPressed: DetailsEvent()
    object OnSave: DetailsEvent()
    object OnUpdate: DetailsEvent()
    data class OnTitleChange(val value: String): DetailsEvent()
    data class OnDescriptionChange(val value: String): DetailsEvent()
}