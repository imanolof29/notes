package com.example.notas.presentation.details

data class DetailsState(
    val id: Int? = null,
    val title: String = "",
    val titleHint: String = "Titulo",
    val description: String = "",
    val descriptionHint: String = "Descripcion",
    val shouldExit: Boolean = false,
    val isEditing: Boolean = false
)