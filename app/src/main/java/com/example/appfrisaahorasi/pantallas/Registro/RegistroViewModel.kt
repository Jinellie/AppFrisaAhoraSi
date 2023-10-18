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
    // CONTROLES
    var showErrorDialog by mutableStateOf(false)
    var dialogMessage by mutableStateOf("")


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

    // SUPPORT FUNCION CREAR
    private fun uploadDataUserOnCreate() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val newDataOfUser = mutableMapOf(
                "User_id" to userId,
                "Display_name" to this.nombre,
                "Email" to this.correo,
                "TipoDeUsuario" to "Persona",
                "celular" to this.celular
            )

            FirebaseFirestore.getInstance().collection("Users")
                .add(newDataOfUser)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Datos subidos satisfactoriamente", "Creado ${task.result?.id}")
                        println("Datos subidos satisfactoriamente")
                        // You can add further logic here, such as navigating to another screen.
                    } else {
                        Log.d("No se subieron los datos", "Error ${task.exception}")
                        // Handle the error, possibly by displaying an error message to the user.
                        println("No se subieron los datos por un error")
                    }
                }
        } else {
            Log.d("No user authenticated", "Data upload failed.")
            // Handle the case where no user is authenticated, possibly by prompting the user to log in.
            print("Fallo al recabar el usuario registrado, no se subieron los datos")
        }
    }

    fun registerUser(navController: NavController) {
        if(contrasena.length < 6){
            showErrorDialog = true
            dialogMessage = "Contraseña invalida"
            return
        }
        if(celular.length != 10){
            showErrorDialog = true
            dialogMessage = "Numero de telefono no es válido"
        }
        for(item in celular){
            if(item <'0' ||  item > '9'){
                showErrorDialog = true
                dialogMessage = "Numero de telefono invalido"
                return
            }
        }

        auth.createUserWithEmailAndPassword(this.correo, this.contrasena)
            .addOnCompleteListener { task ->
                if(_loading.value == false){
                    _loading.value = true;
                    if (task.isSuccessful) {
                        // El usuario se registró con éxito
                        uploadDataUserOnCreate()
                        navController.navigate("EscogerEtiquetasScreen")
                        println("Registrado con éxito")
                    } else {
                        // Ocurrió un error al registrar al usuario
                        navController.navigate("Inicio")
                        //scaffoldState.snackbarHostState.showSnackbar("Fallo en el inicio de sesión")
                        println("Fallo en el registro -> Main Page por default")
                    }
                    _loading.value = false;
                }
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
