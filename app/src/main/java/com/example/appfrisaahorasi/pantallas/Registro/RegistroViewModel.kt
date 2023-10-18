package com.example.appfrisaahorasi.pantallas.Registro

import android.net.Uri
import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.appfrisaahorasi.navigation.NavRoutes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

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

    private fun uploadDataOrganizacion(){
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val newDataOfUser = mutableMapOf(
                "User_id" to userId,
                "Encargado" to this.nombre,
                "Email" to this.correo,
                "TipoDeUsuario" to "Organizacion",
                "celular" to this.celular,
                "Direccion" to this.direccion,
                "NombreOrganizacion" to this.nombreOrg,
                "HoraInicio" to this.horaInicio,
                "HoraFin" to this.horaFin,
                "Dias" to "PorImplementarEnCodigoEsto",
                "Instagram" to this.instagram,
                "Facebook" to this.facebook,
                "Twitter" to this.twitter,
                "Tags" to "TO DO"
            )

            FirebaseFirestore.getInstance().collection("Users")
                .add(newDataOfUser)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Datos subidos satisfactoriamente", "Creado ${task.result?.id}")
                    } else {
                        Log.d("No se subieron los datos", "Error ${task.exception}")
                    }
                }
        } else {
            Log.d("No user authenticated", "Data upload failed.")
        }
    }

    private fun registrarOrganizacion(navController: NavController){
        // Verificación
        auth.createUserWithEmailAndPassword(this.correo, this.contrasena)
            .addOnCompleteListener { task ->
                if(_loading.value == false){
                    _loading.value = true
                    if (task.isSuccessful) {
                        // El usuario se registró con éxito
                        uploadDataOrganizacion()
                        navController.navigate("EscogerEtiquetasScreen")
                    } else {
                        // Ocurrió un error al registrar al usuario
                        navController.navigate("Inicio")
                        //scaffoldState.snackbarHostState.showSnackbar("Fallo en el inicio de sesión")
                    }
                    _loading.value = false
                }
            }

    }
    fun validateContinue1(navController: NavController){
        if(!IsValidCelular() || !IsValidPass()){
            return
        }
        navController.navigate(NavRoutes.registroSC2)
    }
    fun validateContinue2(navController: NavController){
        if(!IsValidTelefono() || !IsValidDescripcion()){
            return
        }
        navController.navigate(NavRoutes.registroSC3)
    }
    fun validateContinue3(navController: NavController){
        if(!IsValidCelular() || !IsValidPass()){
            return
        }
        navController.navigate(NavRoutes.registroSC4)
    }
    fun validateContinue4(navController: NavController){
        // Etiquetas
        registrarOrganizacion(navController)
        navController.navigate(NavRoutes.historialBusqueda)
    }


    fun registerUser(navController: NavController) {
        if(!IsValidCelular() || !IsValidPass()){
            return
        }
        auth.createUserWithEmailAndPassword(this.correo, this.contrasena)
            .addOnCompleteListener { task ->
                if(_loading.value == false){
                    _loading.value = true
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
                    _loading.value = false
                }
            }
    }

    fun finishRegisterUser(onSuccess: () -> Unit) {
        val ageInt: Int = edad.toInt()
        if (ageInt < 0 || ageInt > 99) {
            showErrorDialog = true
            dialogMessage = "Edad no es valida"
            return
        }
        if (descripcion.length > 300) {
            showErrorDialog = true
            dialogMessage = "La descripción es muy larga"
            return
        }
        viewModelScope.launch {
            try {
                val userId = auth.currentUser?.uid

                if (userId != null) {
                    val newDataOfUser = mutableMapOf(
                        "edad" to edad,
                        "description" to descripcion
                    )

                    // Busca el documento que tiene el User_id que coincide con userId
                    FirebaseFirestore.getInstance().collection("Users")
                        .whereEqualTo("User_id", userId)
                        .limit(1) // Solo esperamos un documento que coincida
                        .get()
                        .addOnSuccessListener { querySnapshot ->
                            if (!querySnapshot.isEmpty) {
                                // Obtiene el primer documento (debería haber solo uno que coincida)
                                val document = querySnapshot.documents[0]

                                // Actualiza el documento encontrado
                                document.reference.update(newDataOfUser as Map<String, Any>)
                                    .addOnCompleteListener { task ->
                                        Log.d(
                                            "Datos actualizados satisfactoriamente",
                                            "Usuario $userId actualizado."
                                        )
                                        onSuccess()

                                    }
                                    .addOnFailureListener { e ->
                                        Log.d("No se actualizaron los datos", "Error ${e.message}")
                                    }
                            } else {
                                Log.d(
                                    "Documento no encontrado",
                                    "No se encontró un documento que coincida con el User_id."
                                )
                            }
                        }
                        .addOnFailureListener { e ->
                            Log.d("Error al buscar el documento", "Error ${e.message}")
                        }
                } else {
                    Log.d("No user authenticated", "Data upload failed.")
                    print("Fallo al subir la ultima parte de los datos")
                }

            } catch (e: Exception) {
                Log.d("Error en Jetpack", "ERROR ${e.localizedMessage}")
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
    // VALIDATIONS
    fun IsValidPass(): Boolean{
        if(contrasena.length < 6){
            showErrorDialog = true
            dialogMessage = "Contraseña invalida"
            return false
        }
        return true

    }
    fun IsValidEmail():Boolean{
        return true
    }

    fun IsValidCelular(): Boolean{
        if(celular.length != 10){
            showErrorDialog = true
            dialogMessage = "Celular no es valido"
            return false
        }
        for(item in celular){
            if(item < '0' || item >'9'){
                showErrorDialog = true
                dialogMessage = "Celular no es valido"
                return false
            }
        }
        return true
    }
    fun IsValidNomOrg(): Boolean{
        if(nombreOrg.length <= 1){
            showErrorDialog = true
            dialogMessage = "El nombre de la organizacion no puede estar vacio"
            return false
        }
        return true
    }
    fun IsValidTelefono(): Boolean{
        if(telefono.length != 10){
            showErrorDialog = true
            dialogMessage = "El telefono de la organizacion no es valido"
            return false
        }
        for(item in telefono){
            if(item < '0' || item >'9'){
                showErrorDialog = true
                dialogMessage = "Celular no es valido"
                return false
            }
        }
        return true
    }

    fun IsValidDescripcion(): Boolean{
        if(descripcion.length < 1){
            showErrorDialog = true
            dialogMessage = "La descripción no puede estar vacia"
            return false
        }
        return true
    }
}
