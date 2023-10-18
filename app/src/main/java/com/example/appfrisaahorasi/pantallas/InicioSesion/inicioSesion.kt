package com.example.appfrisaahorasi.pantallas.InicioSesion

import android.content.Intent
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.example.appfrisaahorasi.LoginActivity
import com.example.appfrisaahorasi.R

@Composable
fun InicioSesion() {
    AndroidView(
        factory = { innerContext ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(innerContext).inflate(R.layout.ingresar_inicio_de_sesion, null)

            val iniciarsesion = view.findViewById<LinearLayout>(R.id.btnLogin)
            iniciarsesion.setOnClickListener {
                val intent = Intent(innerContext, LoginActivity::class.java)
                innerContext.startActivity(intent)
            }

            view
        },
        modifier = Modifier.fillMaxSize()
            .background(Color.White) // Set the background color here
    )
}