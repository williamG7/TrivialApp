package com.example.trivialapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val dificultad: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreguntasViewModel::class.java)) {
            return PreguntasViewModel(dificultad) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}