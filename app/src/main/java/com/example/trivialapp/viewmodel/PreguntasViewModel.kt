package com.example.trivialapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.os.CountDownTimer
import com.example.trivialapp.model.Pregunta
import com.example.trivialapp.model.PreguntasRepositorio
import com.example.trivialapp.model.Respuesta

class PreguntasViewModel(dificultad: String) : ViewModel() {

    // Contador de preguntas respondidas
    private val _contador = MutableLiveData(1)
    val contador: LiveData<Int> = _contador

    // Lista de preguntas que se usarán en el juego
    private var preguntas: MutableList<Pregunta> = mutableListOf()

    // Pregunta actual que se muestra en la pantalla
    private val _actualPregunta = MutableLiveData<Pregunta?>()
    val actualPregunta: LiveData<Pregunta?> = _actualPregunta

    // Puntuación del usuario
    private val _puntuacion = MutableLiveData(0)
    val puntuacion: LiveData<Int> = _puntuacion

    // Respuestas disponibles para la pregunta actual
    private val _respuestas = MutableLiveData<List<Respuesta>>()
    val respuestas: LiveData<List<Respuesta>> = _respuestas

    // Variables del temporizador
    private val _tiempoRestante = MutableLiveData(10)
    val tiempoRestante: LiveData<Int> = _tiempoRestante

    // Indica si se han acabado las preguntas
    private val _finPreguntas = MutableLiveData(false)
    val finPreguntas: LiveData<Boolean> = _finPreguntas

    // Temporizador
    private var timer: CountDownTimer? = null

    init {
        // Obtener preguntas según la dificultad
        preguntas = when (dificultad) {
            "Facil" -> PreguntasRepositorio().getPreguntaFacil()
            "Medio" -> PreguntasRepositorio().getPreguntaNormal()
            "Dificil" -> PreguntasRepositorio().getPreguntaDificil()
            else -> mutableListOf()
        }

        preguntas.shuffle() // Mezcla las preguntas para que aparezcan en orden aleatorio
        siguientePregunta() // Carga la primera pregunta
    }

    fun siguientePregunta() {
        timer?.cancel() // Cancela el temporizador actual

        if (preguntas.isNotEmpty()) {
            _actualPregunta.value = preguntas.removeAt(0) // Toma la siguiente pregunta
            _respuestas.value = _actualPregunta.value?.respuesta?.shuffled() // Mezcla las respuestas
            _contador.value = (_contador.value ?: 0) + 1 // Incrementa el contador de preguntas
            iniciarTemporizador() // Inicia el temporizador
        } else {
            _actualPregunta.value = null
            _respuestas.value = emptyList()
            _finPreguntas.value = true
        }
    }

    fun verificarRespuesta(respuesta: Respuesta) {
        timer?.cancel() // Detiene el temporizador al responder

        if (respuesta.correcta) {
            _puntuacion.value = (_puntuacion.value ?: 0) + 10 // Suma puntos si la respuesta es correcta
        }
        siguientePregunta()
    }

    private fun iniciarTemporizador() {
        _tiempoRestante.value = 10 // Reinicia el contador a 10 segundos

        timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _tiempoRestante.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                // Tiempo agotado, pasa a la siguiente pregunta
                siguientePregunta()
            }
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel() // Asegura que el temporizador se cancele cuando el ViewModel se destruya
    }
}