package com.example.appfrisaahorasi.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Queue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appfrisaahorasi.ui.theme.RedApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import androidx.navigation.NavController
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.navigation.NavRoutes


@Composable
fun AppDrawer(userType: String, navController: NavController) {

    //val navController = rememberNavController()
    // Define the selected drawer content Composable
    val drawerContent: @Composable () -> Unit = when (userType) {
        "OSC" -> { { OSCDrawerContent(navController) } }
        "Persona" -> { { StandardUserDrawerContent(navController) } }
        else -> { { InvitadoDrawerContent(navController) } }
    }

    // Display the selected drawer content Composable
    Column {
        drawerContent()
    }
}

@Composable
fun OSCDrawerContent(navController: NavController) {
    // Column Composable
    Column(
        modifier = Modifier
            .background(RedApp)
            .fillMaxSize()
    ) {
        // Navigation button with icon
        NavigationButton(
            text = "Home",
            icon = Icons.Default.Home, // Replace with your desired icon
            onClick = { navController.navigate("home") }
        )

        NavigationButton(
            text = "Perfil",
            icon = Icons.Default.Person, // Replace with your desired icon
            onClick = { navController.navigate("perfilOrgvistaUsuario") }
        )

        NavigationButton(
            text = "Editar Perfil",
            icon = Icons.Default.Edit, // Replace with your desired icon
            onClick = { /* Handle click */ }
        )

        NavigationButton(
            text = "Publicar",
            icon = Icons.Default.Queue, // Replace with your desired icon
            onClick = { /* Handle click */ }
        )

        NavigationButton(
            text = "Cerrar Sesión",
            icon = Icons.Default.Logout, // Replace with your desired icon
            onClick = {
                logoutUser()
                navController.navigate("Inicio") }
        )

        NavigationButton(
            text = "Aviso de Privacidad",
            icon = Icons.Default.Info, // Replace with your desired icon
            onClick = { navController.navigate("avisoPrivacidad") }
        )

        // Add more navigation buttons as needed
    }
}

@Composable
fun StandardUserDrawerContent(navController: NavController) {
    // Column Composable
    Column(
        modifier = Modifier
            .background(RedApp)
            .fillMaxSize()
    ) {
        // Navigation button with icon
        NavigationButton(
            text = "Home",
            icon = Icons.Default.Home, // Replace with your desired icon
            onClick = { navController.navigate("home") }
        )

        NavigationButton(
            text = "Perfil",
            icon = Icons.Default.Person, // Replace with your desired icon
            onClick = { navController.navigate("perfilUsuario") }
        )

        NavigationButton(
            text = "Favoritos",
            icon = Icons.Default.Favorite, // Replace with your desired icon
            onClick = { navController.navigate("favoritos") }
        )

        NavigationButton(
            text = "Cerrar Sesión",
            icon = Icons.Default.Logout, // Replace with your desired icon
            onClick = {
                logoutUser()
                navController.navigate("Inicio") }
        )

        NavigationButton(
            text = "Aviso de Privacidad",
            icon = Icons.Default.Info, // Replace with your desired icon
            onClick = { navController.navigate("avisoPrivacidad") }
        )

        // Add more navigation buttons as needed
    }
}

@Composable
fun InvitadoDrawerContent(navController: NavController) {
    // Column Composable
    Column(
        modifier = Modifier
            .background(RedApp)
            .fillMaxSize()
    ) {
        // Navigation button with icon
        NavigationButton(
            text = "Home",
            icon = Icons.Default.Home, // Replace with your desired icon
            onClick = { navController.navigate("home") }
        )

        NavigationButton(
            text = "Iniciar Sesión",
            icon = Icons.Default.Login, // Replace with your desired icon
            onClick = { navController.navigate("InicioSesion") }
        )

        NavigationButton(
            text = "Registrarse",
            icon = Icons.Default.People, // Replace with your desired icon
            onClick = { navController.navigate("Registro") }
        )

        // Add more navigation buttons as needed
    }
}

@Composable
fun NavigationButton(text: String, icon: ImageVector, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .height(60.dp)
            .padding(20.dp, 0.dp, 0.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null, // Optional content description
            modifier = Modifier.size(24.dp), // Icon size
            tint = Color.White
        )
        Text(
            text = text,
            modifier = Modifier.padding(15.dp),
            color = Color.White,
            fontSize = 18.sp
        )
    }
}


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

private fun logoutUser() {
    val auth = Firebase.auth
    auth.signOut()
    // You can navigate to the login screen or any other screen after logout
    // Example: navController.navigate("login")
}

data class MyComposableParams(val text: String = "")
/*@Preview
@Composable
fun MyComposablePreview(@PreviewParameter(MyComposableParamsProvider::class) params: MyComposableParams) {
    AppDrawer(userType = params.text)
}

class MyComposableParamsProvider : PreviewParameterProvider<MyComposableParams> {
    override val values: Sequence<MyComposableParams> = sequenceOf(
        MyComposableParams(text = "")
    )
}
*/