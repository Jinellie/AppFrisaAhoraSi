package com.example.appfrisaahorasi.pantallas.Perfil

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.pantallas.AppDrawer
import kotlinx.coroutines.launch


@Composable
fun PerfilUsuario(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(userType = "Persona", navController)
        },
        content = { padding ->
            ContentPerfil(
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
fun ContentPerfil(navController: NavController, modifier: Modifier, onMenuClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.profile_user, null)

            // ACTIVAR EL NAVDRAWER
            val tresBarrasButton = view.findViewById<ImageView>(R.id.tresBarras)
            tresBarrasButton.setOnClickListener {
                onMenuClick()  // Esto abrirá o cerrará el cajón
            }

            view
        },
        modifier = Modifier.fillMaxSize()
            .background(Color.White) // Set the background color here

    )
}



