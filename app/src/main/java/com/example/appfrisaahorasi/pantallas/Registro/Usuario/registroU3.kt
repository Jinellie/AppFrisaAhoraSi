package com.example.appfrisaahorasi.pantallas.Registro.Usuario

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.pantallas.Registro.Organizacion.CustomTextField
import com.example.appfrisaahorasi.pantallas.Registro.RegistroViewModel


class RegistroU3Activity : ComponentActivity() {
    private val viewModel: RegistroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroU3Screen()
        }
    }

}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistroU3Screen() {
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

                Text(text = "Complementa tu perfil con la siguiente información.",
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 15.sp)


                CustomTextField(
                    value = viewModel.descripcion,
                    onValueChange = { newValue -> viewModel.onDescripcionChanged(newValue)},
                    placeholder = "Descripción",
                    keyboardType = KeyboardType.Text
                )

                CustomTextField(
                    value = viewModel.edad,
                    onValueChange = { newValue -> viewModel.onEdadChanged(newValue)},
                    placeholder = "Edad",
                    keyboardType = KeyboardType.Number
                )


                Image(
                    painter = painterResource(id = R.drawable.genprofilepic),
                    contentDescription = "Foto de perfil genérica",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Button(
                    onClick = {
                        // Launch the image picker
                        // Inside the button click listener or where you want to trigger image selection
                        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                        intent.type = "image/*"

                    },
                    shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(red = 0.7216145992279053f, green = 0.015033637173473835f, blue = 0.015033637173473835f, alpha = 0.7900000214576721f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(275.dp)
                        .padding(start = 18.dp, top = 6.dp, end = 18.dp, bottom = 6.dp)
                ) {
                    Text(text = "Elige una foto de perfil", color = Color.White)
                }




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