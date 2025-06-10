package com.example.trivialapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
    var showWarning by remember { mutableStateOf(false) }
    var seleccionarDificultad by remember { mutableStateOf("") }
    val dificultades = listOf("Fácil", "Normal", "Difícil")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = "Fondo del menu",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido principal con fondo blanco semitransparente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp)
                .background(Color.White.copy(alpha = 0.7f)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título
            Text(
                text = "TRIVIAL",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 50.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Imagen del logo
            Image(
                painter = painterResource(id = R.drawable.logotriviados),
                contentDescription = "Logo Trivial",
                modifier = Modifier.size(180.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Menú desplegable con el estilo del segundo código pero usando tus variables
            DropMenu(
                dificultades = dificultades,
                selectedText = seleccionarDificultad
            ) { seleccionarDificultad = it }

            Spacer(modifier = Modifier.height(24.dp))

            // Mensaje de advertencia
            if (showWarning) {
                Text(
                    "Por favor, selecciona una dificultad",
                    color = Color.Red,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Botón iniciar partida con tu estilo y lógica
            Button(
                onClick = {
                    if (seleccionarDificultad.isEmpty()) {
                        showWarning = true
                    } else {
                        showWarning = false
                        navigateToNext(seleccionarDificultad)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(text = "Iniciar Partida", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun DropMenu(
    dificultades: List<String>,
    selectedText: String,
    selectedDificultad: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            value = if (selectedText.isEmpty()) "DIFICULTAD" else selectedText,
            onValueChange = {},
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Black))
                .clickable { expanded = true },
            textStyle = TextStyle(
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            shape = RoundedCornerShape(25.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Black)),
            containerColor = Color.White,
            shape = RoundedCornerShape(25.dp)
        ) {
            dificultades.forEach { dificultad ->
                DropdownMenuItem(
                    text = {
                        Text(
                            dificultad,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black
                        )
                    },
                    onClick = {
                        selectedDificultad(dificultad)
                        expanded = false
                    }
                )
            }
        }
    }
}
