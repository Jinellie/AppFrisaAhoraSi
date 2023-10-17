package com.example.appfrisaahorasi.pantallas.busquedaBar

import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
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
fun Brusque(navController: NavController) {
    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.activity_main, null)

            // Encuentra el botón por su ID y configura el onClickListener
            val searchBar = view.findViewById<LinearLayout>(R.id.searchBar)
            searchBar.setOnClickListener {
                navController.navigate(NavRoutes.busquedaTags)
            }

            view
        },
        modifier = Modifier.fillMaxSize()
            .background(Color.White) // Set the background color here
    )
}
