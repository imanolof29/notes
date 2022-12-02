package com.example.notas.util

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Details: Screen("details")
}