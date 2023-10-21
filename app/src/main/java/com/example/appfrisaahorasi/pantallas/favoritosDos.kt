package com.example.appfrisaahorasi.pantallas

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            AppDrawer(userType = "Persona", navController)
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

    val  listaDeOSC = listOf(
        OSC(
            userName = "Diez por Ciénega A.C.",
            imageUrl = "https://picsum.photos/id/0/200",

            ),
        OSC(
            userName = "Diez por Ciénega A.C.",
            imageUrl = "https://picsum.photos/id/0/200",

            ),
        OSC(
            userName = "Organización Solidaria A.B.",
            imageUrl = "https://picsum.photos/id/1/200",

            ),
        OSC(
            userName = "Fundación Esperanza X.Y.Z.",
            imageUrl = "https://picsum.photos/id/2/200",

            ),
        OSC(
            userName = "Asociación Pro Medio Ambiente",
            imageUrl = "https://picsum.photos/id/3/200",

            ),
        OSC(
            userName = "Acción Humanitaria B.C.D.",
            imageUrl = "https://picsum.photos/id/4/200",

            ),
        OSC(
            userName = "Fundación Sonrisas E.F.G.",
            imageUrl = "https://picsum.photos/id/5/200",

            ),
        OSC(
            userName = "Ayuda para Personas Mayores H.I.J.",
            imageUrl = "https://picsum.photos/id/6/200",

            ),
        OSC(
            userName = "Fundación Educación K.L.M.",
            imageUrl = "https://picsum.photos/id/7/200",

            ),
        OSC(
            userName = "Organización Cultural N.O.P.",
            imageUrl = "https://picsum.photos/id/8/200",

            ),
        OSC(
            userName = "Fundación de Salud Q.R.S.",
            imageUrl = "https://picsum.photos/id/9/200",
        )
    )
    LazyColumnWithOSCCards(users = listaDeOSC, navController)

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

