package com.example.appfrisaahorasi.pantallas.Registro

import android.net.Uri
import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroViewModel : ViewModel() {
    // GENERAL
    var nombre by mutableStateOf("")
    var celular by mutableStateOf("") // celular al que se vincula la cuenta
    var correo by mutableStateOf("")
    var contrasena by mutableStateOf("")
    var profilePictureUri by mutableStateOf<Uri?>(null)

    // USUARIO PERSONA
    var edad by  mutableStateOf("")

    // USUARIO ORGANIZACION
    var nombreOrg by mutableStateOf("") // nombre de la organización
    var direccion by mutableStateOf("")
    //  Horario de atencion
    var horaInicio by mutableStateOf("")
    var horaFin by mutableStateOf("")
    //  Redes sociales
    var instagram by mutableStateOf("")
    var twitter by mutableStateOf("")
    var facebook by mutableStateOf("")
    //
    var telefono by mutableStateOf("") // telefono de una organizacion
    var descripcion by mutableStateOf("") // descripcion para perfil de usuario o org




    private val auth = FirebaseAuth.getInstance()
    //private val firestore = FirebaseFirestore.getInstance()
    // FUNCION DE REGISTRO
    private val _loading = MutableLiveData(false)
    fun registerUser(navController: NavController) {
        auth.createUserWithEmailAndPassword(this.correo, this.contrasena)
            .addOnCompleteListener { task ->
                if(_loading.value == false){
                    _loading.value = true;
                    if (task.isSuccessful) {
                        // El usuario se registró con éxito
                        uploadDataUserOnCreate(nombre, correo)
                        navController.navigate("EscogerEtiquetasScreen")
                        println("Registrado con éxito")
                    } else {
                        // Ocurrió un error al registrar al usuario
                        navController.navigate("Inicio")
                        //scaffoldState.snackbarHostState.showSnackbar("Fallo en el inicio de sesión")
                        println("Fallo en el registro")
                    }
                    _loading.value = false;
                }
            }
    }
    // SUPPORT FUNCION CREAR
    private fun uploadDataUserOnCreate(name: String, email: String){
        // Vector<String> arr = {Nombre, Telefono, Correo, TipoDeUsuario, }
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()

        // Asignar
        user["User_id"] = userId.toString()
        user["Display_name"] = name
        user["Email"] = email
        user["TipoDeUsuario"] = "Persona"

        // Referenciar la colección que creamos en firestore database
        FirebaseFirestore.getInstance().collection("Users")
            .add(user)
            .addOnSuccessListener {
                Log.d("Datos subidos satisfactoriamente", "Creado ${it.id}")
            }
            .addOnFailureListener{
                Log.d("Datos subidos satisfactoriamente", "Error ${it}")
            }
    }

    fun onNombreChanged(newNombre: String) {
        nombre = newNombre
    }

    fun onInstagramChanged(newInstagram: String) {
        instagram = newInstagram
    }
    fun onTwitterChanged(newTwitter: String) {
        twitter = newTwitter
    }
    fun onFacebookChanged(newFacebook: String) {
        facebook = newFacebook
    }

    fun onHoraInicioChanged(newInicio: String) {
        horaInicio = newInicio
    }
    fun onHoraFinChanged(newFin: String) {
        horaFin = newFin
    }

    fun onDescripcionChanged(newDescripcion: String) {
        descripcion = newDescripcion
    }

    fun onCelularChanged(newCelular: String) {
        celular = newCelular
    }

    fun onCorreoChanged(newCorreo: String) {
        correo = newCorreo
    }

    fun onDireccionChanged(newDireccion: String) {
        direccion = newDireccion
    }

    fun onNombreOrgChanged(newNombreOrg: String) {
        nombreOrg = newNombreOrg
    }

    fun onContrasenaChanged(newContrasena: String) {
        contrasena = newContrasena
    }

    fun onTelefonoChanged(newTelefono: String) {
        telefono = newTelefono
    }

    fun onEdadChanged(newEdad: String) {
        edad = newEdad
    }

}
