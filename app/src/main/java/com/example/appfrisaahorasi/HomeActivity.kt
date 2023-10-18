package com.example.appfrisaahorasi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Retrieve the "tipoUsuario" parameter from the intent
        val tipoUsuario = intent.getStringExtra("tipoUsuario")

        Log.i("Home, signed in","signInWithCredential:success")


        //val navHostViewModel: NavHostViewModel by viewModels()
        //val navController = navHostViewModel.navController
        // Navegar a home
        //navController.navigate("home")


    }
}
