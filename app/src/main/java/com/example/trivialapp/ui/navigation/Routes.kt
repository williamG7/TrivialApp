package com.example.trivialapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Pantalla1

@Serializable
data class Pantalla2(val dificultad: String)

@Serializable
data class Pantalla3(val puntuacion: Int)


