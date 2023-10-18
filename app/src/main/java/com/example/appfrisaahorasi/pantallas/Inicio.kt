package com.example.appfrisaahorasi.pantallas

import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.navigation.NavRoutes

@Composable
fun Inicio(navController: NavController) {
    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.inicio_de_sesion, null)

            // LOG IN
            val inicioDeSesionButton = view.findViewById<LinearLayout>(R.id.idsbutton)
            inicioDeSesionButton.setOnClickListener {
                navController.navigate(NavRoutes.InicioSesion)
            }

            // REGISTRO
            val registroButton = view.findViewById<LinearLayout>(R.id.regbutton)
            registroButton.setOnClickListener {
                navController.navigate(NavRoutes.IniciarRegistro)
            }

            // INVITADO
            val invitadoButton = view.findViewById<TextView>(R.id.invButton)
            invitadoButton.setOnClickListener {
                navController.navigate(NavRoutes.historialBusqueda)
            }
            view
        },
        modifier = Modifier.fillMaxSize()
            //.background(Color.White) // Set the background color here
    )
}

