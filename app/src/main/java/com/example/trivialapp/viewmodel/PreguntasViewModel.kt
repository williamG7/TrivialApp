package com.example.trivialapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivialapp.model.Pregunta
import com.example.trivialapp.model.PreguntasRepositorio
import com.example.trivialapp.model.Respuesta
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job

class PreguntasViewModel(dificultad: String) : ViewModel() {

    // contador de preguntas respondidas
    private var _contador = MutableLiveData(1)
    val contador: LiveData<Int> = _contador

    // lista de preguntas que se usaran en el juego
    private var preguntas: MutableList<Pregunta> = mutableListOf()

    // pregunta actual que se muestra en la pantalla
    private val _actualPregunta = MutableLiveData<Pregunta>()
    val actualPregunta: LiveData<Pregunta> = _actualPregunta

    // puntuacion del usuario
    private val _puntuacion = MutableLiveData(0)
    val puntuacion: LiveData<Int> = _puntuacion

    // respuestas disponibles para la pregunta actual
    private val _respuestas = MutableLiveData<List<Respuesta>>()
    val respuestas: LiveData<List<Respuesta>> = _respuestas

    // variables del temporizador
    private var tiempo: Int = 10
    private var job: Job? = null
    private val _progreso = MutableLiveData<Float>(1f) // 1.0 representa 100%
    val progreso: LiveData<Float> = _progreso

    init {
        // obtener preguntas segun la dificultad
        preguntas = when (dificultad) {
            "Facil" -> PreguntasRepositorio().getPreguntas1()
            "Medio" -> PreguntasRepositorio().getPreguntas2()
            "Dificil" -> PreguntasRepositorio().getPreguntas3()
            else -> mutableListOf()
        }

        preguntas.shuffle() // mezcla las preguntas para que aparezcan en orden aleatorio
        siguientePregunta()
    }

    fun siguientePregunta() {
        if (preguntas.isNotEmpty()) {
            _actualPregunta.value = preguntas.removeAt(0) // toma la siguiente pregunta
            _respuestas.value = _actualPregunta.value?.respuesta?.shuffled() // mezcla las respuestas
            _contador.value = (_contador.value ?: 0) + 1 // incrementa el contador de preguntas
            iniciarTemporizador() // inicia el temporizador
        }
    }

    fun verificarRespuesta(respuesta: Respuesta) {
        if (respuesta.correcta) {
            _puntuacion.value = (_puntuacion.value ?: 0) + 1 // suma puntos si la respuesta es correcta
        }
        siguientePregunta()
    }

    private fun iniciarTemporizador() {
        job?.cancel() // cancela cualquier temporizador previo
        job = viewModelScope.launch {
            tiempo = 10
            while (tiempo > 0) {
                delay(1000L) // espera un segundo
                tiempo--
                _progreso.value = tiempo / 10f // actualiza la barra de progreso
            }
            verificarRespuesta(Respuesta("", false)) // si se acaba el tiempo, pasa a la siguiente pregunta
        }
    }
}
