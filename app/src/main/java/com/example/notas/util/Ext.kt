package com.example.notas.util

import com.example.notas.data.local.entity.Priority

fun Priority.toText(): String {
    return when(this) {
        Priority.LOW -> "Baja"
        Priority.MEDIUM -> "Media"
        Priority.HIGH -> "Alta"
    }
}