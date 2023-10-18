package com.example.appfrisaahorasi.pantallas

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.navigation.NavRoutes
import kotlinx.coroutines.launch


@Composable
fun FavsDos(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(userType = "Personal", navController)
        },
        content = { padding ->
            ContentDos(
                navController,
                modifier = Modifier.padding(padding),
                onMenuClick = {
                    coroutineScope.launch {
                        if (scaffoldState.drawerState.isOpen) {
                            scaffoldState.drawerState.close()
                        } else {
                            scaffoldState.drawerState.open()
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun ContentDos(navController: NavController, modifier: Modifier, onMenuClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.favs_osc, null)

            // ACTIVAR EL NAVDRAWER
            val tresBarrasButton = view.findViewById<ImageView>(R.id.tresBarras)
            tresBarrasButton.setOnClickListener {
                onMenuClick()  // Esto abrirá o cerrará el cajón
            }

            val otraPantalla = view.findViewById<ImageView>(R.id.otroFavs)
            otraPantalla.setOnClickListener {
                navController.navigate(NavRoutes.favoritos)
            }

            view
        },
        modifier = Modifier.fillMaxSize()
        //.background(Color.White) // Set the background color here
    )
}

