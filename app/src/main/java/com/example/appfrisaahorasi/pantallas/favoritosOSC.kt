package com.example.appfrisaahorasi.pantallas

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.navigation.NavRoutes
import kotlinx.coroutines.launch


@Composable
fun FavsOSC(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(userType = "Persona", navController)
        },
        content = { padding ->
            ContentFavOrgs(
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
fun ContentFavOrgs(navController: NavController, modifier: Modifier, onMenuClick: () -> Unit) {

    val  listaDeOSC = listOf(
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
    LazyColumnWithOSCCards(users = listaDeOSC, navController = navController)

    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.favs, null)

            // ACTIVAR EL NAVDRAWER
            val tresBarrasButton = view.findViewById<ImageView>(R.id.tresBarras)
            tresBarrasButton.setOnClickListener {
                onMenuClick()  // Esto abrirá o cerrará el cajón
            }

            val otraPantalla = view.findViewById<ImageView>(R.id.otroFavs)
            otraPantalla.setOnClickListener {
                navController.navigate(NavRoutes.favOrgs)
            }

            view
        },
        modifier = Modifier.fillMaxSize()
        //.background(Color.White) // Set the background color here
    )
}
@Composable
fun RoundedOSCCard(user: OSC, navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).clickable(enabled = true, onClick = { navController.navigate("perfilOrgvistaUsuario") })
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imagePainter = rememberAsyncImagePainter(model = user.imageUrl)
                Image(
                    painter = imagePainter,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = user.userName, fontSize = 16.sp)
            }

        }
    }
}
data class OSC(val userName: String, val imageUrl: String)

@Composable
fun LazyColumnWithOSCCards(users: List<OSC>, navController: NavController) {
    LazyColumn (contentPadding = PaddingValues(horizontal = 0.dp, vertical = 50.dp)) {

        items(users) { user ->
            RoundedOSCCard(user = user, navController)
        }
    }
}