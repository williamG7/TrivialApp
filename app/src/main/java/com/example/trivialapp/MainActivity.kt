package com.example.trivialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.trivialapp.ui.theme.TrivialAppTheme
import com.example.trivialapp.ui.navigation.NavigationWrapper


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        Thread.sleep(3000 )
        enableEdgeToEdge()
        setContent {
            TrivialAppTheme() {
                NavigationWrapper()
            }
        }
    }
}




