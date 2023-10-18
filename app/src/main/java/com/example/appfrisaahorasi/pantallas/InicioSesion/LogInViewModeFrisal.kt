package com.example.appfrisaahorasi.pantallas.InicioSesion

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class LogInViewModelFrisa: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    var contrasena by mutableStateOf("")
    var LogInEmail by mutableStateOf("")
    fun login( onSuccess: () -> Unit){
        viewModelScope.launch {
            try{
                auth.signInWithEmailAndPassword(LogInEmail,contrasena)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            onSuccess()
                        }
                        else{
                            println(LogInEmail)
                            Log.d("Email: ", LogInEmail)
                            Log.d("Pass: ", contrasena)
                            Log.d("Error en FIrebase", "Usuario o contraseña incorrecta ")
                        }
                    }
            }catch(e: Exception){
                Log.d("Error en Jetpack", "ERROR ${e.localizedMessage}")
            }
        }
    }

    // SET
    fun onContrasenaChanged(_contrasena: String) {
        contrasena = _contrasena
    }

    fun onEmailChanged( _email: String) {
        LogInEmail = _email
    }
}