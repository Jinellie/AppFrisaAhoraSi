package com.example.appfrisaahorasi.pantallas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.VisualTransformation

class RegistroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Registro",
                    fontSize = 28.sp
                )

                TextField(
                    value = viewModel.nombre,
                    onValueChange = { newValue -> viewModel.onNombreChanged(newValue) },
                    label = { Text("Nombre") },
                    colors = TextFieldDefaults.textFieldColors(
                        //backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.3f)
                        backgroundColor = Color(red = 0.37578123807907104f, green = 0.37578123807907104f, blue = 0.37578123807907104f, alpha = 0.20999999344348907f),
                        focusedIndicatorColor = Color.Transparent, // Elimina el indicador de enfoque
                        unfocusedIndicatorColor = Color.Transparent, // Elimina el indicador sin enfoque
                        cursorColor = Color.Black // Cambia el color del cursor si es necesario
                    ),
                    modifier = Modifier.fillMaxWidth()
                        .width(275.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp))
                        .padding(start = 18.dp, top = 6.dp, end = 18.dp, bottom = 6.dp),
                    shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp),
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
                var passwordHidden by rememberSaveable { mutableStateOf(true) }

                TextField(
                    value = viewModel.contrasena,
                    onValueChange = { newValue -> viewModel.onContrasenaChanged(newValue) },
                    singleLine = true,
                    visualTransformation =
                    if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon =
                                if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            // Please provide localized description for accessibility services
                            val description = if (passwordHidden) "Show password" else "Hide password"
                            Icon(imageVector = visibilityIcon, contentDescription = description)
                        }
                    },
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
@Preview
@Composable
fun testStyle(){


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        modifier = Modifier

            .width(275.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp))
            .background(Color(red = 0.37578123807907104f, green = 0.37578123807907104f, blue = 0.37578123807907104f, alpha = 0.20999999344348907f))

            .padding(start = 18.dp, top = 6.dp, end = 18.dp, bottom = 6.dp)

            .alpha(1f)


    ) {
    }


}



