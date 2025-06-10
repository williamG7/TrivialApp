package com.example.trivialapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trivialapp.R
import com.example.trivialapp.model.Pregunta
import com.example.trivialapp.viewmodel.PreguntasViewModel
import com.example.trivialapp.viewmodel.ViewModelFactory

@Composable
fun GameScreen(dificultad: String, navigateToResult: (Int) -> Unit) {
    val preguntaViewModel: PreguntasViewModel = viewModel(factory = ViewModelFactory(dificultad))
    val actualPregunta: Pregunta? by preguntaViewModel.actualPregunta.observeAsState()
    val respuestas by preguntaViewModel.respuestas.observeAsState(emptyList())
    val finPreguntas by preguntaViewModel.finPreguntas.observeAsState(false)
    val timeLeft by preguntaViewModel.timeLeft.observeAsState(10)
    val contador by preguntaViewModel.rondas.observeAsState(1)
    val puntuacion by preguntaViewModel.puntuacion.observeAsState(0)

    if (finPreguntas || contador == 11) {
        navigateToResult(puntuacion)
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.arcade_background),
                contentDescription = "fondo de pantalla",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x55000000))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "Ronda: $contador / 10",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                actualPregunta?.let {
                    Card(
                        modifier = Modifier.fillMaxWidth(0.85f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF222222))
                    ) {
                        Text(
                            it.texto,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(20.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                respuestas.forEach { respuesta ->
                    OutlinedButton(
                        onClick = {
                            preguntaViewModel.actualizarPuntuacion(respuesta.correcta)
                            preguntaViewModel.siguientePregunta()
                            preguntaViewModel.updateRondas()
                            preguntaViewModel.updateTimeLeft(10)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(respuesta.respuesta)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                LinearProgressIndicator(
                    progress = timeLeft / 10f,
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(0.8f),
                    color = Color(0xFF000000),
                    trackColor = Color(0xFFFAFAFA)
                )
            }
        }
    }
}
