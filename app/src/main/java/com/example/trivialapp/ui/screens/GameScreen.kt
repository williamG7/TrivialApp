package com.example.trivialapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trivialapp.R
import com.example.trivialapp.model.Pregunta
import com.example.trivialapp.ui.theme.TrivialAppTheme
import com.example.trivialapp.viewmodel.PreguntasViewModel
import com.example.trivialapp.viewmodel.ViewModelFactory

@Composable
fun GameScreen(dificultad: String, navigateToGame: (Int) -> Unit) {
    val preguntaViewModel: PreguntasViewModel = viewModel(factory = ViewModelFactory(dificultad)) // obtiene el viewmodel con la dificultad seleccionada
    val actualPregunta: Pregunta? by preguntaViewModel.actualPregunta.observeAsState() // obtiene la pregunta actual
    val contador: Int? by preguntaViewModel.contador.observeAsState(1) // lleva el control del numero de pregunta
    val puntuacion: Int by preguntaViewModel.puntuacion.observeAsState(0) // almacena la puntuacion
    val progreso: Float? by preguntaViewModel.progreso.observeAsState() // progreso de la partida

    // si se llega a la ronda 11, se navega a la siguiente pantalla con la puntuacion obtenida
    if (contador == 11) {
        navigateToGame(puntuacion)
    } else {
        actualPregunta?.let { preguntas ->
            val respuestasDesordenadas = preguntas.respuesta.shuffled() // mezcla las respuestas para que no siempre aparezcan en el mismo orden

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // imagen de fondo de la pantalla del juego
                Image(
                    painter = painterResource(id = R.drawable.arcade_background),
                    contentDescription = "fondo de pantalla",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // contenido principal del juego
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x55000000)), // agrega un fondo semitransparente encima de la imagen
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // muestra el numero de ronda
                    Text(
                        "ronda ${contador} / 10",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.White
                    )

                    // muestra la pregunta actual
                    Text(
                        text = preguntas.texto,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // muestra las opciones de respuesta
                    Column {
                        respuestasDesordenadas.forEach { respuesta ->
                            OutlinedButton(
                                onClick = { preguntaViewModel.verificarRespuesta(respuesta) }, // verifica si la respuesta es correcta
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Black,
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(vertical = 4.dp)
                            ) {
                                Text(respuesta.respuesta)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // barra de progreso de la partida
                    LinearProgressIndicator(
                        progress = { progreso ?: 0f }, // usa el progreso actual o 0 si es nulo
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
}

// vista previa de la pantalla del juego con la dificultad "facil"
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameScreenPreview() {
    TrivialAppTheme {
        GameScreen(dificultad = "facil") { }
    }
}
