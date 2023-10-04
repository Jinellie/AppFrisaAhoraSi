package com.example.appfrisaahorasi.pantallas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
// import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class RegistroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroScreen()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistroScreen() {
    val viewModel: RegistroViewModel = viewModel()

    Scaffold( // nav bar iría aquí
        topBar = {
            TopAppBar(
                title = { Text(text = "Registro") }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Registro",
                    fontSize = 28.sp
                )

                OutlinedTextField(
                    value = viewModel.nombre,
                    onValueChange = { newValue -> viewModel.onNombreChanged(newValue) },
                    singleLine = true,
                    textStyle = TextStyle.Default, // Puedes personalizar esto según tus necesidades
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onDone = { /* Acción al presionar Enter/Done */ })
                )

                BasicTextField(
                    value = viewModel.celular,
                    onValueChange = { newValue -> viewModel.onCelularChanged(newValue) },
                    singleLine = true,
                    textStyle = TextStyle.Default, // Puedes personalizar esto según tus necesidades
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone
                    ),
                    keyboardActions = KeyboardActions(onDone = { /* Acción al presionar Enter/Done */ })
                )

                BasicTextField(
                    value = viewModel.correo,
                    onValueChange = { newValue -> viewModel.onCorreoChanged(newValue) },
                    singleLine = true,
                    textStyle = TextStyle.Default, // Puedes personalizar esto según tus necesidades
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(onDone = { /* Acción al presionar Enter/Done */ })
                )

                BasicTextField(
                    value = viewModel.contrasena,
                    onValueChange = { newValue -> viewModel.onContrasenaChanged(newValue) },
                    singleLine = true,
                    textStyle = TextStyle.Default, // Puedes personalizar esto según tus necesidades
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(onDone = { /* Acción al presionar Enter/Done */ })
                )

                BasicTextField(
                    value = viewModel.repetirContrasena,
                    onValueChange = { newValue -> viewModel.onRepetirContrasenaChanged(newValue) },
                    singleLine = true,
                    textStyle = TextStyle.Default, // Puedes personalizar esto según tus necesidades
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(onDone = { /* Acción al presionar Enter/Done */ })
                )

/*                BasicTextField(
                    value = viewModel.repetirContrasena,
                    onValueChange = { viewModel.onRepetirContrasenaChanged(it) },
                    singleLine = true,
                    placeholder = { Text("Repetir Contraseña") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    )
                )*/

                Button(
                    onClick = { /* Realizar registro aquí */ },
                ) {
                    Text(text = "Registrarse")
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun RegistroScreenPreview() {
    RegistroScreen()
}


class RegistroViewModel : ViewModel() {
    var nombre by mutableStateOf("")
    var celular by mutableStateOf("")
    var correo by mutableStateOf("")
    var contrasena by mutableStateOf("")
    var repetirContrasena by mutableStateOf("")

    fun onNombreChanged(newNombre: String) {
        nombre = newNombre
    }

    fun onCelularChanged(newCelular: String) {
        celular = newCelular
    }

    fun onCorreoChanged(newCorreo: String) {
        correo = newCorreo
    }

    fun onContrasenaChanged(newContrasena: String) {
        contrasena = newContrasena
    }

    fun onRepetirContrasenaChanged(newRepetirContrasena: String) {
        repetirContrasena = newRepetirContrasena
    }
}


