package com.example.trivialapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.trivialapp.ui.screens.GameScreen
import com.example.trivialapp.ui.screens.MenuScreen
import com.example.trivialapp.ui.screens.ResultadoScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, Pantalla1) {
        composable<Pantalla1> {
            MenuScreen { dificultad -> navController.navigate(Pantalla2(dificultad)) }
        }
        composable<Pantalla2> {backStackEntry ->
            val pantalla2 = backStackEntry.toRoute<Pantalla2>()
            GameScreen(pantalla2.dificultad){ puntuacion -> navController.navigate(Pantalla3(puntuacion))}
        }
        composable<Pantalla3> { backStackEntry ->
            val pantalla3 = backStackEntry.toRoute<Pantalla3>()
            ResultadoScreen(pantalla3.puntuacion) { navController.navigate(Pantalla1){
                popUpTo<Pantalla1>{ inclusive= true } } }
        }
    }
}



