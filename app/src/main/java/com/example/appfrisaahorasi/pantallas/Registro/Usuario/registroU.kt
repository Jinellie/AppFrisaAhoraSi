package com.example.appfrisaahorasi.pantallas.Registro.Usuario

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.CustomTextField
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.PasswordTextField
import com.example.appfrisaahorasi.pantallas.Registro.RegistroViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistroUScreen(
    navController: NavController,
    viewModel: RegistroViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    )
    {
    val viewModel: RegistroViewModel = viewModel()

    Scaffold( // nav bar iría aquí
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                backgroundColor = Color(red = 0.7216145992279053f, green = 0.015033637173473835f, blue = 0.015033637173473835f, alpha = 0.7900000214576721f)
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
                    fontSize = 40.sp, // Adjust the size as needed
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(text = "Ingrese sus datos en los siguientes campos",
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 15.sp)
                Text(
                    text = "NOMBRE Y APELLIDOS *",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(18.dp, 6.dp, 18.dp)
                        .width(207.dp)
                        //.height(18.dp)
                        .alpha(1f),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )

                CustomTextField(
                    value = viewModel.nombre,
                    onValueChange = { newValue -> viewModel.onNombreChanged(newValue)},
                    placeholder = " ",
                    keyboardType = KeyboardType.Text
                )

                Text(
                    text = "MEDIO DE CONTACTO",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(18.dp, 6.dp, 18.dp)
                        .width(150.dp)
                        //.height(18.dp)
                        .alpha(1f),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )
                CustomTextField(
                    value = viewModel.celular,
                    onValueChange = { newValue -> viewModel.onCelularChanged(newValue) },
                    placeholder = "Celular *",
                    keyboardType = KeyboardType.Phone
                )

                CustomTextField(
                    value = viewModel.correo,
                    onValueChange = { newValue -> viewModel.onCorreoChanged(newValue) },
                    placeholder = "Correo (Opcional)",
                    keyboardType = KeyboardType.Email
                )

                var passwordHidden by rememberSaveable { mutableStateOf(true) }
                Text(
                    text = "CONTRASEÑA *",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(18.dp, 6.dp, 18.dp)
                        .width(100.dp)
                        //.height(18.dp)
                        .alpha(1f),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )
                PasswordTextField(
                    value = viewModel.contrasena,
                    onValueChange = { newValue -> viewModel.onContrasenaChanged(newValue) },
                    placeholder = "Minimo 6 caracteres",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    passwordVisibility = passwordHidden
                )

                Button(
                    onClick = {viewModel.registerUser(navController)},
                    shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(red = 0.7216145992279053f, green = 0.015033637173473835f, blue = 0.015033637173473835f, alpha = 0.7900000214576721f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(275.dp)
                        .padding(start = 18.dp, top = 6.dp, end = 18.dp, bottom = 6.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 2.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp,
                        hoveredElevation = 4.dp,
                        focusedElevation = 4.dp
                    )
                ) {
                    Text(text = "Continuar", color = Color.White)
                }
            }
        }
    )
}