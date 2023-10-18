package com.example.appfrisaahorasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appfrisaahorasi.navigation.NavRoutes
import com.example.appfrisaahorasi.navigation.NavRoutes.LogInFrisa

// Pantallas - Se base en el nombre de la clase, no el nombre del archivo
import com.example.appfrisaahorasi.pantallas.AvisodePrivacidadScreen
import com.example.appfrisaahorasi.pantallas.Home
import com.example.appfrisaahorasi.pantallas.Busqueda.BrusqueTags
import com.example.appfrisaahorasi.pantallas.Busqueda.HistorialBrusqre
import com.example.appfrisaahorasi.pantallas.Inicio
import com.example.appfrisaahorasi.pantallas.InicioSesion.InicioSesion
import com.example.appfrisaahorasi.pantallas.InicioSesion.LogInFrisa
import com.example.appfrisaahorasi.pantallas.Perfil.PerfilApp
import com.example.appfrisaahorasi.pantallas.Perfil.PerfilUsuario
import com.example.appfrisaahorasi.pantallas.Registro.Registro
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.RegistroSC2Screen
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.RegistroSC3Screen
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.RegistroSC4Screen
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.RegistroScreen
import com.example.appfrisaahorasi.pantallas.Registro.Usuario.RegistroU3Screen
import com.example.appfrisaahorasi.pantallas.Registro.Usuario.RegistroUScreen
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.registroSC5
import com.example.appfrisaahorasi.pantallas.Registro.Usuario.favOrgs
import com.example.appfrisaahorasi.pantallas.busquedaBar.Brusque
import com.example.appfrisaahorasi.ui.theme.AppFrisaAhoraSiTheme
import com.example.appfrisaahorasi.pantallas.Registro.EscogerEtiquetasScreen

class MainActivity : ComponentActivity() {
    // NAV CONTROLLER TO ACCESS ALL
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFrisaAhoraSiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()



                    // NAVIGATION MANAGER SCREENS
                    navController?.let { controller ->

                        NavHost(navController, startDestination = NavRoutes.Inicio) {
                            //  Main page
                            composable(NavRoutes.Inicio) {
                                Inicio(navController = controller)
                            }
                            // INICIO SESION
                            composable(NavRoutes.InicioSesion) {
                                InicioSesion(navController = controller)
                            }
                            // Inicio con correo
                            composable(NavRoutes.LogInFrisa){
                                LogInFrisa(navController = controller)
                            }

                            // Aviso de privacidad
                            composable(NavRoutes.avisoPrivacidad) {
                                AvisodePrivacidadScreen()
                            }
                            // Perfil USUARIO
                            composable(NavRoutes.perfilOrgvistaUsuario) {
                                PerfilApp("CarlosMex", "Somos una organización comprometida con la excelencia y la satisfacción del cliente. Nuestra misión es proporcionar soluciones innovadoras y de alta calidad en manufactura, aprovechando la experiencia de nuestro talentoso equipo y la última tecnología. Nos enorgullece nuestro compromiso con la integridad, la responsabilidad social y la sostenibilidad. Nuestra visión es liderar en [insertar aquí la área de liderazgo o enfoque estratégico], proporcionando un entorno de trabajo inspirador para nuestros empleados y generando un impacto positivo en la comunidad y el mundo en general. Estamos dedicados a alcanzar la excelencia en cada aspecto de nuestro negocio y a establecer relaciones a largo plazo con nuestros clientes, socios y partes interesadas",
                                                "www.teitter.com", "fbeds", "sdsdsd", navController = controller)

                            }
                            composable(NavRoutes.perfilUsuario) {
                                PerfilUsuario()
                            }
                            // REGISTRO EMPEZAR
                            composable(NavRoutes.IniciarRegistro) {
                                Registro(navController = controller)
                            }
                            // REGISTRO: ORGANIZACIÓN
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

                            // REGISTRO: USUARIO
                            composable(NavRoutes.registroU) {
                                RegistroUScreen(navController)
                            }
                            composable(NavRoutes.EscogerEtiquetasScreen) {
                                EscogerEtiquetasScreen()
                            }
                            composable(NavRoutes.registroU3) {
                                RegistroU3Screen()
                            }
                            // Busqueda
                            composable(NavRoutes.busqueda){
                                Brusque(navController = controller)
                            }
                            composable(NavRoutes.busquedaTags){
                                BrusqueTags(navController = controller)
                            }
                            composable(NavRoutes.favOrgs){
                                favOrgs(navController = controller)
                            }
                            composable(NavRoutes.favoritos){
                                favOrgs(navController = controller)
                            }

                            composable(NavRoutes.historialBusqueda){
                                HistorialBrusqre(navController = controller)
                            }
                            // HOME
                            composable(NavRoutes.home) {
                                Home()
                            }
                            composable(NavRoutes.favoritos) {
                              //  favoritos()
                            }
                        } // Fin NavHost
                    } // Fin NavControllerCreation
                } // Fin cuerpo surface
            }// Fin App frisa
                } // Fin Set Content
            } // Fin ON Create
        } // Fin clase








