package com.example.appfrisaahorasi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Retrieve the "tipoUsuario" parameter from the intent
        val tipoUsuario = intent.getStringExtra("tipoUsuario")

        // Retrieve the NavController from the shared ViewModel
        //val navController = NavHostViewModel.navController

        // When you want to navigate to a different Composable:
       // navController.navigate("home")


    }
}
