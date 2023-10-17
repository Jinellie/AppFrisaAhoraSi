package com.example.appfrisaahorasi.pantallas.Busqueda

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.appfrisaahorasi.R

@Composable
fun BrusqueTags(navController: NavController) {
    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.activated_bar, null)

            // Encuentra el botón por su ID y configura el onClickListener
//            val searchBar = view.findViewById<Button>(R.id.searchBar)
//            searchBar.setOnClickListener {
//                navController.navigate(NavRoutes.)
//            }

            view
        },
        modifier = Modifier.fillMaxSize()
            .background(Color.White) // Set the background color here
    )
}