package com.example.appfrisaahorasi.pantallas

import android.view.LayoutInflater
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

            // Encuentra el botón por su ID y configura el onClickListener
            val registroButton = view.findViewById<Button>(R.id.BtnIniciarRegistro)
            registroButton.setOnClickListener {
                navController.navigate(NavRoutes.IniciarRegistro)
            }

            view
        },
        modifier = Modifier.fillMaxSize()
            .background(Color.White) // Set the background color here
    )
}