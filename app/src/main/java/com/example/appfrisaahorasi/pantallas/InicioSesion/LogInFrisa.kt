package com.example.appfrisaahorasi.pantallas.InicioSesion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appfrisaahorasi.navigation.NavRoutes
import com.example.appfrisaahorasi.ui.theme.RedApp
import com.example.appfrisaahorasi.pantallas.InicioSesion.LogInViewModelFrisa
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.CustomTextField
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.PasswordTextField


@Composable
fun LogInFrisa(navController: NavController){
    val loginVM : LogInViewModelFrisa = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        var email by remember { mutableStateOf("")}
        var password by remember { mutableStateOf("")}

        Text(
            text = "Correo",
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
            color = Color.Black
        )

        CustomTextField(
            value = loginVM.LogInEmail,
            onValueChange = { newValue -> loginVM.onEmailChanged(newValue) },
            placeholder = "",
            keyboardType = KeyboardType.Text
        )

        Text(
            text = "Contraseña",
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
            color = Color.Black
        )

        CustomTextField(
            value = loginVM.contrasena,
            onValueChange = { newValue -> loginVM.onContrasenaChanged(newValue) },
            placeholder = "",
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
                loginVM.login(){
                    navController.navigate(NavRoutes.home)
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = RedApp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)){
            Text(text = "Iniciar Sesión", color = Color.White)
        }
    }
}

