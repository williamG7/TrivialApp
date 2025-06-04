package com.example.trivialapp.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trivialapp.R

@Composable
fun ResultadoScreen(puntuacion: Int, navigateToMenu: () -> Unit) {
    val context = LocalContext.current // obtiene el contexto de la aplicacion

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.arcade_background),
            contentDescription = "fondo de pantalla",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // contenido principal con fondo semitransparente
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0x55000000)), // color con transparencia para mejorar visibilidad
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // muestra la puntuacion obtenida
            Text(
                "tu puntuacion es de: $puntuacion",
                fontWeight = FontWeight.Thin,
                fontSize = 35.sp,
                color = Color.White // cambio a blanco para mayor contraste
            )

            Spacer(modifier = Modifier.height(16.dp))

            // boton para compartir la puntuacion
            OutlinedButton(
                onClick = { compartirPuntuacion(context, puntuacion) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text("compartir resultado")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // boton para volver al menu principal
            OutlinedButton(
                onClick = { navigateToMenu() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text("volver a jugar")
            }
        }
    }
}

// funcion para compartir la puntuacion en otras aplicaciones
fun compartirPuntuacion(context: Context, puntuacion: Int) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "mi puntuacion en TrivialApp es de $puntuacion puntos, ¿puedes hacerlo mejor?")
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, "compartir con")) // muestra el selector de aplicaciones para compartir
}
