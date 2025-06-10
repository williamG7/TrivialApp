package com.example.trivialapp.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivialapp.model.Pregunta
import com.example.trivialapp.model.PreguntasRepositorio
import com.example.trivialapp.model.Respuesta
import kotlinx.coroutines.launch

class PreguntasViewModel(dificultad: String) : ViewModel() {

    private var preguntas: MutableList<Pregunta> = mutableListOf()

    private val _actualPregunta = MutableLiveData<Pregunta?>()
    val actualPregunta: LiveData<Pregunta?> = _actualPregunta

    private val _puntuacion = MutableLiveData<Int>()
    val puntuacion: LiveData<Int> = _puntuacion

    private val _respuestas = MutableLiveData<List<Respuesta>>()
    val respuestas: LiveData<List<Respuesta>> = _respuestas

    private val _finPreguntas = MutableLiveData<Boolean>()
    val finPreguntas: LiveData<Boolean> = _finPreguntas

    private val _timeLeft = MutableLiveData(10)
    val timeLeft: LiveData<Int> get() = _timeLeft

    private val _rondas = MutableLiveData(1)
    val rondas: LiveData<Int> get() = _rondas

    private var timer: CountDownTimer? = null

    fun updateTimeLeft(time: Int) {
        _timeLeft.value = time
    }

    fun updateRondas() {
        _rondas.value = (_rondas.value ?: 1) + 1
    }

    private fun startTimer() {
        viewModelScope.launch {
            timer?.cancel()
            timer = object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    updateTimeLeft((millisUntilFinished / 1000).toInt())
                }

                override fun onFinish() {
                    updateRondas()
                    siguientePregunta()
                }
            }.start()
        }
    }

    init {
        when (dificultad) {
            "Facil" -> preguntas.addAll(PreguntasRepositorio(dificultad).getPreguntaFacil())
            "Medio" -> preguntas.addAll(PreguntasRepositorio(dificultad).getPreguntaNormal())
            "Dificil" -> preguntas.addAll(PreguntasRepositorio(dificultad).getPreguntaDificil())
        }
        preguntas.shuffle()
        _puntuacion.value = 0
        _finPreguntas.value = false
        siguientePregunta()
    }

    fun siguientePregunta() {
        if (preguntas.isNotEmpty()) {
            _actualPregunta.value = preguntas.first()
            preguntas.removeAt(0)
            _respuestas.value = _actualPregunta.value?.respuesta?.toList()?.shuffled()
            startTimer()
        } else {
            _actualPregunta.value = null
            _respuestas.value = emptyList()
            _finPreguntas.value = true
            timer?.cancel()
        }
    }

    fun actualizarPuntuacion(correcta: Boolean) {
        if (correcta) {
            _puntuacion.value = (_puntuacion.value ?: 0) + 10
        }
    }

    fun responder(esCorrecta: Boolean) {
        actualizarPuntuacion(esCorrecta)
        updateRondas()
        siguientePregunta()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}
