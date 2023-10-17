package com.example.appfrisaahorasi.pantallas.Perfil

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.ui.theme.Black
import com.example.appfrisaahorasi.ui.theme.RedApp
import com.google.android.gms.maps.MapView
//import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.White) {
                // Scaffold we created
                PerfilApp()
            }
//            MapScreen()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PerfilApp() {

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
            Body()
        },

        // pass the drawer
        drawerContent = {
            Drawer()
        },

        floatingActionButton = {
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
            }
        }
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

@Composable
fun MapScreen() {
    val mapView = rememberMapViewWithLifecycle()

    // on below line creating a
    // column for our maps.
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        // on below line adding a map view to it.
        AndroidView({ mapView }) { mapView ->
            // on below line launching our map view
            CoroutineScope(Dispatchers.Main).launch {
                //val map = mapView.awaitMap()
                // on below line adding zoom controls for map.
                //map.uiSettings.isZoomControlsEnabled = true
            }
        }
    }
}

// A function which will receive a callback to trigger to opening the drawer
@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    // TopAppBar Composable
    TopAppBar(
        title = {
            Text(text = "Search...", color = Color.White)
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.clickable {
                    onMenuClicked()
                },
                tint = Color.White
            )
        },
        backgroundColor = RedApp
    )
}


@Composable
fun BottomBar() {
    val customColor = RedApp // Hex color code

    // BottomAppBar Composable
    BottomAppBar(
        backgroundColor = customColor
    ) {
        // Center the text horizontally and vertically
        Text(
            text = "Suscribirme",
            color = Color.White,
            modifier = Modifier
                .padding(8.dp) // Add padding for spacing
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun Body() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "¡Siguenos!",
            color = Black,
            modifier = Modifier
                .padding(8.dp) // Agrega margen para espaciar
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            style = TextStyle(fontSize = 24.sp) // Ajusta el tamaño del texto aquí
        )

        // Links
        Text(
            text = "Ubicación",
            color = Black,
            modifier = Modifier
                .padding(8.dp) // Agrega margen para espaciar
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            style = TextStyle(fontSize = 24.sp) // Ajusta el tamaño del texto aquí
        )
        Image(
            painter = painterResource(id = R.drawable.google_maps),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp).
                fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

//        MapScreen()

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



@Preview
@Composable
fun PerfilPreview() {
    PerfilApp()
}

//data class organizationOSF{
//    val Name: String,
//    val tel: Int,
//    val direction: String,
//    val urlFacebook: String,
//    val urlTwitter: String,
//    val urlInstagram: String
//}