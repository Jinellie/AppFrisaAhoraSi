package com.example.appfrisaahorasi.pantallas.InicioSesion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appfrisaahorasi.ui.theme.RedApp


@Composable
fun LoginView(navController: NavController, loginVM: LogInViewModelFrisa ){
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        var email by remember { mutableStateOf("")}
        var password by remember { mutableStateOf("")}
        
        OutlinedTextField(value = email, onValueChange = {email = it},
            label = {Text(text = "Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )
        OutlinedTextField(value = password, onValueChange = {password = it},
            label = {Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
                loginVM.login(email, password){
                    navController.navigate("home")
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = RedApp),
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)){
            Text(text = "Log In")
        }
    }
}

