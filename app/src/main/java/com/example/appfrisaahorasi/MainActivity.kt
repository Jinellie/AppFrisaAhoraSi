package com.example.appfrisaahorasi

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appfrisaahorasi.navigation.NavRoutes
import com.example.appfrisaahorasi.pantallas.AvisodePrivacidadScreen
import com.example.appfrisaahorasi.pantallas.Home
import com.example.appfrisaahorasi.pantallas.InicioSesion1
import com.example.appfrisaahorasi.pantallas.InicioSesion2
import com.example.appfrisaahorasi.pantallas.PerfilApp
import com.example.appfrisaahorasi.pantallas.PerfilUsuario
import com.example.appfrisaahorasi.pantallas.RegistroSC2Screen
import com.example.appfrisaahorasi.pantallas.RegistroSC3Screen
import com.example.appfrisaahorasi.pantallas.RegistroSC4Screen
import com.example.appfrisaahorasi.pantallas.RegistroScreen
import com.example.appfrisaahorasi.pantallas.RegistroU3Screen
import com.example.appfrisaahorasi.pantallas.RegistroUScreen
import com.example.appfrisaahorasi.pantallas.TipodeUsuario
import com.example.appfrisaahorasi.pantallas.registroSC5
import com.example.appfrisaahorasi.pantallas.registroU2
import com.example.appfrisaahorasi.ui.theme.AppFrisaAhoraSiTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFrisaAhoraSiTheme {
                val navController = rememberNavController()

                // Initialize the shared ViewModel and set the NavController
                val viewModel: NavHostViewModel = viewModel()
                viewModel.navController = navController

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(navController, startDestination = NavRoutes.inicioSesion) {
                        composable(NavRoutes.inicioSesion) {
                            InicioSesion1()
                        }
                        composable(NavRoutes.inicioSesion2) {
                            InicioSesion2()
                        }
                        composable(NavRoutes.avisoPrivacidad) {
                            AvisodePrivacidadScreen()
                        }
                        composable(NavRoutes.perfilOrgvistaUsuario) {
                            PerfilApp()
                        }
                        composable(NavRoutes.perfilUsuario) {
                            PerfilUsuario()
                        }
                        composable(NavRoutes.registroSC) {
                            RegistroScreen()
                        }
                        composable(NavRoutes.registroSC2) {
                            RegistroSC2Screen()
                        }
                        composable(NavRoutes.registroSC3) {
                            RegistroSC3Screen()
                        }
                        composable(NavRoutes.registroSC4) {
                            RegistroSC4Screen()
                        }
                        composable(NavRoutes.registroSC5) {
                            registroSC5()
                        }
                        composable(NavRoutes.registroU) {
                            RegistroUScreen()
                        }
                        composable(NavRoutes.registroU2) {
                            registroU2()
                        }
                        composable(NavRoutes.registroU3) {
                            RegistroU3Screen()
                        }
                        composable(NavRoutes.tipodeUsuario) {
                            TipodeUsuario()
                        }
                        composable(NavRoutes.home) {
                            Home()
                        }
                    }
                }
            }
        }
    }

    fun navigateToInicioSesion2(view: View) {
        // Your click event handling code here
    }


}

class NavHostViewModel : ViewModel() {
    lateinit var navController: NavHostController

    companion object {
        //var navController: NavHostController
    }
}



