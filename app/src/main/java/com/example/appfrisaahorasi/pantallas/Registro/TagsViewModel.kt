package com.example.appfrisaahorasi.pantallas.Registro


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class NavigationFlow {
    REGISTRATION_USER,
    REGISTRATION_ORG,
    PROFILE
}

class TagsViewModel : ViewModel() {
    val navigationFlow = MutableLiveData(NavigationFlow.PROFILE) // Valor por defecto
    val tagsList = mutableStateOf<List<String>>(listOf())
    private val auth = FirebaseAuth.getInstance()
    init {
        fetchTags()
    }

    private fun fetchTags() {
        val collectionRef = FirebaseFirestore.getInstance().collection("Tags")
        val documentId = "f6af1fkY6a8tHuWYCwgG"

        collectionRef.document(documentId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val data = document.data
                    val tags = data?.get("tags") as List<String>?
                    if (tags != null) {
                        tagsList.value = tags
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle error here
            }
    }

    fun uploadDataTags(navController: NavController, selectedTags: List<String>, flow: NavigationFlow) {
        // Tipo de accion: Si es 1 es Registro de Usuario, 2 es Registro de Orgnizacion, 3 es actualizacion de datos nada más, regresar al perfil
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val newDataOfUser = mutableMapOf(
                "tags" to selectedTags
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

                        val tipoDeUsuario = document.getString("TipoDeUsuario")
                        if (tipoDeUsuario != null) {
                            when(flow) {
                                NavigationFlow.REGISTRATION_USER -> {
                                    if (tipoDeUsuario == "Persona") {
                                        // Navegar a la continuación del registro de usuario
                                        navController.navigate("registroU3")
                                    } else {
                                        // Handle unexpected scenario or error
                                    }
                                }
                                NavigationFlow.REGISTRATION_ORG -> {
                                    if (tipoDeUsuario == "Organizacion") {
                                        // Navegar a la continuación del registro de organización
                                        navController.navigate("RegistroOrganizacionContinuacionScreen")
                                    } else {
                                        // Handle unexpected scenario or error
                                    }
                                }
                                NavigationFlow.PROFILE -> {
                                    if (tipoDeUsuario == "Persona") {
                                        // Navegar al perfil de usuario
                                        navController.navigate("PerfilUsuarioScreen")
                                    } else if (tipoDeUsuario == "Organizacion") {
                                        // Navegar al perfil de organización
                                        navController.navigate("PerfilOrganizacionScreen")
                                    }
                                }
                            }
                        }

                        // Actualiza el documento encontrado
                        document.reference.update(newDataOfUser as Map<String, Any>)
                            .addOnSuccessListener {
                                Log.d("Datos actualizados satisfactoriamente", "Usuario $userId actualizado.")
                                println("Datos actualizados satisfactoriamente")
                            }
                            .addOnFailureListener { e ->
                                Log.d("No se actualizaron los datos", "Error ${e.message}")
                                println("No se actualizaron los datos por un error")
                            }
                    } else {
                        Log.d("Documento no encontrado", "No se encontró un documento que coincida con el User_id.")
                        println("Documento no encontrado")
                    }
                }
                .addOnFailureListener { e ->
                    Log.d("Error al buscar el documento", "Error ${e.message}")
                    println("Error al buscar el documento")
                }
        } else {
            Log.d("No user authenticated", "Data upload failed.")
            print("Fallo al recabar el usuario registrado, no se subieron los datos")
        }
    }


}
