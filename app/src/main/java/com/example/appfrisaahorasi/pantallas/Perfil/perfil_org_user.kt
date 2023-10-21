package com.example.appfrisaahorasi.pantallas.Perfil

//import com.google.maps.android.ktx.awaitMap
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.pantallas.AppDrawer
import com.example.appfrisaahorasi.pantallas.TopBar
import com.example.appfrisaahorasi.ui.theme.Black
import com.example.appfrisaahorasi.ui.theme.RedApp
import com.google.android.gms.maps.MapView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
var NombreOrg = "Nombre_Org"
var OrgDesc = "Hola soy una organizacion....Hola soy una organizacion....Hola soy una organizacion....Hola soy una organizacion....Hola soy una organizacion...."
var instaUrl = "https://www.instagram.com"
var twitterUrl = "https://twitter.com"
var faceBookUrl = "https://www.facebook.com"*/

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun PerfilApp(NombreOrg: String, OrgDesc : String, instaUrl : String, twitterUrl: String, facebookUrl: String,  navController: NavController ) {

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // Create a coroutine scope. Opening of Drawer
    // and snackbar should happen in background
    // thread without blocking main thread
    val coroutineScope = rememberCoroutineScope()



    // Scaffold Composable
    Scaffold(
        // pass the scaffold state
        scaffoldState = scaffoldState,
        // pass the topbar we created
        topBar = {
            TopBar(
                // When menu is clicked open the
                // drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                })
        },

        // pass the bottomBar we created
        bottomBar = { BottomBar() },

        // Pass the body in content parameter
        content = {
            Body(NombreOrg, OrgDesc, instaUrl, twitterUrl, facebookUrl, navController)
        },

        // pass the drawer
        drawerContent = {
            AppDrawer("OSC", navController) // TODO: pasar parámetro tipoUsuario
        },

        /*floatingActionButton = {
            // Create a floating action button in
            // floatingActionButton parameter of scaffold
            FloatingActionButton(
                onClick = {
                    // When clicked open Snackbar
                    coroutineScope.launch {
                        when (scaffoldState.snackbarHostState.showSnackbar(
                            // Message In the snackbar
                            message = "Snack Bar",
                            actionLabel = "Dismiss"
                        )) {
                            SnackbarResult.Dismissed -> {
                                // do something when
                                // snack bar is dismissed
                            }

                            SnackbarResult.ActionPerformed -> {
                                // when it appears
                            }

                        }
                    }
                }) {
                // Simple Text inside FAB
                Text(text = "X")
            }*/
      //  }
    )
}
@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    // on below line initializing
    // our maps view with id.
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map
        }
    }

    // Makes MapView follow the lifecycle of this composable
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)

    // on below line initializing lifecycle variable.
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    // on below line adding observer for lifecycle.
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
    // returning map view on below line.
    return mapView
}
@Composable
// creating a function for map lifecycle observer.
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        // on below line adding different events for maps view
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }





// A function which will receive a callback to trigger to opening the drawer



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomBar() {
    val customColor = RedApp // Hex color code

    // BottomAppBar Composable
    BottomAppBar(
        backgroundColor = customColor
    ) {
        // Agregar un botón para guardar los datos del usuario en Firebase cuando se haga clic en "Suscribirme"
        Card(
            onClick = {
                val email = "frisa@gmail.com" // Dirección de correo

                // Crear un mapa con los datos que deseas guardar
                val userData = mapOf(
                    "correo" to email,
                    "correoUsuario" to "tadeo34.barrera@gmail.com",
                    "date" to "20 Oct, 2023",
                    "favoritos" to "18 de octubre de 2023, 00:00:00 UTC-6",
                    "name" to "Adrián Tadeo Barrera Almanza",
                    "shipTo" to "Mex, N.L.",
                    "telefono" to "8117975841"
                )

                // Guardar los datos en la colección "listaDifusionCorreo"
                FirebaseFirestore.getInstance().collection("listaDifusion")
                    .document(email)
                    .set(userData)
                    .addOnSuccessListener {
                        // Manejar el éxito de la operación
                        println("Datos del usuario guardados en listaDifusion")
                    }
                    .addOnFailureListener { e ->
                        // Manejar cualquier error
                        println("Error al guardar los datos del usuario en listaDifusion: $e")
                    }
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            backgroundColor = Color.Transparent,
            elevation =  0.dp
        ) {
            Text(text = "Suscribirme",         color = Color.White
            )
        }
    }
}

@Composable
fun Body(NombreOrg: String, OrgDesc : String, instaUrl : String, twitterUrl: String, facebookUrl: String,  navController: NavController ) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var isImageClicked by remember { mutableStateOf(false) }
    val shareIntent = remember { Intent(Intent.ACTION_SEND) }
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareUrl)
    shareIntent.type = "text/plain"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {


            Image(
                painter = painterResource(id = R.drawable.organizacion),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )

            Text(
                text = NombreOrg,
                color = Black,
                modifier = Modifier.padding(top = 27.dp),
                style = TextStyle(fontSize = 24.sp)

            )
            Spacer(modifier = Modifier.width(20.dp))

            Image(
                painter = painterResource(id = R.drawable.share_icon),
                contentDescription = null,
                modifier = Modifier.padding(top = 27.dp).size(40.dp)
                    .clickable{
                        ContextCompat.startActivity(
                            context,
                            Intent.createChooser(shareIntent, null),
                            null
                        )

                    }
            )

            Spacer(modifier = Modifier.width(10.dp))



            Image(
                painter = painterResource(if (!isImageClicked) R.drawable.heart_black else R.drawable.empty_heart),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 27.dp)
                    .size(40.dp)
                    .clickable {
                        isImageClicked = !isImageClicked
                    })

        }




        Text(
            text = OrgDesc,
            color = Black,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 15.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            style = TextStyle(fontSize = 14.sp)
        )

        Spacer(modifier = Modifier.width(37.dp))


        Text(
            text = "¡Siguenos!",
            color = Black,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            style = TextStyle(fontSize = 24.sp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {



            Image(
                painter = painterResource(id = R.drawable.instagram),
                contentDescription = null,
                modifier = Modifier.padding(top = 15.dp).size(40.dp)

                    .clickable(){
                        coroutineScope.launch {
                            val intent = Intent(Intent.ACTION_VIEW, instaUrl.toUri())

                            startActivity(context, intent, null)

                        }
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.twitter),
                contentDescription = null,
                modifier = Modifier.padding(top = 15.dp).size(40.dp)
                    .clickable(){
                        coroutineScope.launch {
                            val intent = Intent(Intent.ACTION_VIEW, twitterUrl.toUri())

                            startActivity(context, intent, null)

                        }
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = null,
                modifier = Modifier.padding(top = 15.dp).size(40.dp)
                    .clickable(){
                        coroutineScope.launch {
                            val intent = Intent(Intent.ACTION_VIEW, facebookUrl.toUri())

                            startActivity(context, intent, null)

                        }
                    }
            )
        }

        Text(
            text = "Ubicación",
            color = Black,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            style = TextStyle(fontSize = 24.sp)
        )

        Image(
            painter = painterResource(id = R.drawable.google_maps),
            contentDescription = null,
            modifier = Modifier.padding(top = 20.dp).size(90.dp)
                .clickable{
                    navController.navigate("map")

                }
        )
    }
}




@Composable
fun Drawer() {
    // Column Composable
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Repeat is a loop which
        // takes count as argument
        repeat(5) { item ->
            Text(text = "Item number $item", modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}






//Funcionamiento del share
var shareUrl = "https://www.linkedin.com/in/adrián-cavazos-guerra-96a141251/"
fun Context.shareLink(url: String) {
    val sendIntent = Intent(
        Intent.ACTION_SEND
    ).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(
        sendIntent, null
    )
    startActivity(shareIntent)
}

//data class organizationOSF{
//    val Name: String,
//    val tel: Int,
//    val direction: String,
//    val urlFacebook: String,
//    val urlTwitter: String,
//    val urlInstagram: String
//}