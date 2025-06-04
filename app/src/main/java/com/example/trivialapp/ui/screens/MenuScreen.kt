package com.example.trivialapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trivialapp.R

@Composable
fun MenuScreen(navigateToNext: (String) -> Unit) {
    var showWarning by remember { mutableStateOf(false) } // variable para mostrar advertencia si no se selecciona dificultad
    var seleccionarDificultad by remember { mutableStateOf("") } // guarda la dificultad seleccionada

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // imagen de fondo del menu
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = "Fondo del menu",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // contenido principal del menu
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp)
                .background(Color.White.copy(alpha = 0.7f)), // fondo blanco semitransparente
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // titulo del menu
            Text(
                text = "TRIVIAL",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 50.sp,
                color = Color.Black,
            )

            // componente personalizado del menu desplegable
            MyDropDownMenu(seleccionarDificultad) {
                seleccionarDificultad = it
            }

            // mensaje de advertencia si no se selecciona una dificultad
            if (showWarning) {
                Text(
                    "Seleccione una dificultad",
                    color = Color.Red,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // boton para iniciar la partida
            Button(
                onClick = {
                    if (seleccionarDificultad.isEmpty()) {
                        showWarning = true // si no se elige dificultad, se muestra la advertencia
                    } else {
                        showWarning = false
                        navigateToNext(seleccionarDificultad) // navega a la pantalla de juego con la dificultad seleccionada
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, color = Color.Black)
            ) {
                Text(text = "Iniciar Partida")
            }
        }
    }
}

@Composable
fun MyDropDownMenu(seleccionarDificultad: String, onValueChange: (String) -> Unit) {
    var expandir by remember { mutableStateOf(false) } // controla si el menu desplegable esta abierto o cerrado
    val dificultades = listOf(
        "NIVEL FACIL",
        "NIVEL MEDIO",
        "NIVEL DIFICIL"
    ) // lista de niveles de dificultad

    Box {
        // campo de texto deshabilitado que se usa como boton para abrir el menu
        OutlinedTextField(
            value = seleccionarDificultad,
            onValueChange = { onValueChange(it) },
            enabled = false, // deshabilitado para que solo se seleccione del menu
            textStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
            readOnly = true,
            label = {
                Text(
                    "Dificultad",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                )
            },
            modifier = Modifier
                .clickable { expandir = true } // hace que al hacer clic se despliegue el menu
                .fillMaxWidth(0.7f)
                .background(color = Color.LightGray, shape = RoundedCornerShape(25.dp))
                .align(Alignment.Center),
            shape = RoundedCornerShape(25.dp)
        )

        // menu desplegable
        DropdownMenu(
            expanded = expandir,
            onDismissRequest = { expandir = false }, // cierra el menu si se hace clic fuera
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .align(Alignment.Center),
            containerColor = Color.White,
            shape = RoundedCornerShape(25.dp)
        ) {
            // crea un item en el menu para cada nivel de dificultad
            dificultades.forEach { dificultad ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = dificultad,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onClick = {
                        expandir = false // cierra el menu al seleccionar una opcion
                        onValueChange(dificultad) // actualiza la dificultad seleccionada
                    }
                )
            }
        }
    }
}
