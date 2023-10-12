package com.example.appfrisaahorasi

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appfrisaahorasi.ui.theme.AppFrisaAhoraSiTheme
import com.example.appfrisaahorasi.navigation.NavRoutes
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFrisaAhoraSiTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*
                    NavHost(navController, startDestination = NavRoutes.RegistrationStep1) {
                        composable(NavRoutes.RegistrationStep1) {
                            RegistroStep1Screen()
                        }
                        composable(NavRoutes.RegistrationStep2) {
                            RegistroStep2Screen()
                        }
                    }*/
                }
            }
        }
    }
}




