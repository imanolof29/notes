package com.example.notas.domain.models

data class Note(
    val id: Int? = null,
    val title: String,
    val description: String?
)