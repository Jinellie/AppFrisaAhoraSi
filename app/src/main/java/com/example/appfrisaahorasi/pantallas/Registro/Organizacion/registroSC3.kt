package com.example.appfrisaahorasi.pantallas.Registro.Organizacion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.pantallas.Registro.RegistroViewModel


class RegistroSC3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroSC3Screen()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val daysOfWeek = arrayOf(
        "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    )
    val selectedItems = remember { mutableStateListOf<String>() }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
           // .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedItems.joinToString(", ") { it },
                onValueChange = {},
                readOnly = true,

                trailingIcon = {
                    if (expanded) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowup), // Replace with your own icon resource
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp) // Adjust the size as needed
                                .padding(end = 8.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowdown), // Use a custom down arrow icon
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp) // Adjust the size as needed
                                .padding(end = 8.dp)
                        )
                    }
                },
                modifier = Modifier.menuAnchor().fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .padding(18.dp, 6.dp, 18.dp, 6.dp),
                shape = RoundedCornerShape(5.dp)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                daysOfWeek.forEach { item ->
                    val isSelected = item in selectedItems
                    Row(
                        modifier = Modifier.clickable {
                            if (isSelected) {
                                selectedItems.remove(item)
                            } else {
                                selectedItems.add(item)
                            }
                        }
                    ) {
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = null
                        )
                        Text(text = item, modifier = Modifier.padding(start = 16.dp))
                    }
                }
            }
        }
    }
}




@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistroSC3Screen() {
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
                    text = "Calendario",
                    fontSize = 40.sp, // Adjust the size as needed
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(text = "Ingrese sus horarios de operación.",
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 15.sp)




                CustomTextField(
                    value = viewModel.horaInicio,
                    onValueChange = { newValue -> viewModel.onHoraInicioChanged(newValue)},
                    placeholder = "Hora de inicio",
                    keyboardType = KeyboardType.Text
                )

                CustomTextField(
                    value = viewModel.horaFin,
                    onValueChange = { newValue -> viewModel.onHoraFinChanged(newValue) },
                    placeholder = "Hora de fin",
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Días comprendidos:",
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 15.sp)


                Demo_ExposedDropdownMenuBox()


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