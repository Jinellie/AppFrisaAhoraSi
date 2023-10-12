package com.example.appfrisaahorasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import com.example.appfrisaahorasi.pantallas.TipodeUsuario
import com.example.appfrisaahorasi.pantallas.RegistroScreen
import com.example.appfrisaahorasi.pantallas.registroU2
import com.example.appfrisaahorasi.ui.theme.AppFrisaAhoraSiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFrisaAhoraSiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipodeUsuario()
                }
            }
        }
    }
}



