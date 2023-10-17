package com.example.appfrisaahorasi.pantallas.Registro.Organizacion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appfrisaahorasi.pantallas.Registro.RegistroViewModel

class RegistroSC2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroSC2Screen()
        }
    }
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistroSC2Screen() {
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
                    text = "Perfil",
                    fontSize = 40.sp, // Adjust the size as needed
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(text = "Ingrese los datos de su organización en los siguientes campos. Esta información será visible en su perfil.",
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 15.sp)


                Text(
                    text = "Obligatorio *",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(18.dp, 6.dp, 18.dp)

                        .width(77.dp)

                        //.height(18.dp)

                        .alpha(1f),


                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )

                CustomTextField(
                    value = viewModel.nombreOrg,
                    onValueChange = { newValue -> viewModel.onNombreOrgChanged(newValue)},
                    placeholder = "Nombre de la organización",
                    keyboardType = KeyboardType.Text
                )

                CustomTextField(
                    value = viewModel.correo,
                    onValueChange = { newValue -> viewModel.onCorreoChanged(newValue) },
                    placeholder = "Correo",
                    keyboardType = KeyboardType.Email
                )

                CustomTextField(
                    value = viewModel.telefono,
                    onValueChange = { newValue -> viewModel.onTelefonoChanged(newValue) },
                    placeholder = "Teléfono",
                    keyboardType = KeyboardType.Phone
                )

                CustomTextField(
                    value = viewModel.direccion,
                    onValueChange = { newValue -> viewModel.onDireccionChanged(newValue) },
                    placeholder = "Dirección",
                    keyboardType = KeyboardType.Text
                )

                CustomTextField(
                    value = viewModel.descripcion,
                    onValueChange = { newValue -> viewModel.onDescripcionChanged(newValue)},
                    placeholder = "Descripción",
                    keyboardType = KeyboardType.Text
                )



                Button(
                    onClick = { /* Realizar registro aquí */ },
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
                    Text(text = "Guardar", color = Color.White)
                }
            }
        }
    )
}