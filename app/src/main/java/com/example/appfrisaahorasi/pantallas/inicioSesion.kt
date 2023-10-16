package com.example.appfrisaahorasi.pantallas

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.appfrisaahorasi.R

@Preview
@Composable
fun InicioSesion() {
    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.ingresar_inicio_de_sesion, null)
            view
        },
        modifier = Modifier.fillMaxSize()
            .background(Color.White) // Set the background color here

    )
}