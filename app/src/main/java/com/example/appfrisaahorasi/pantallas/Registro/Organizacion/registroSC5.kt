package com.example.appfrisaahorasi.pantallas.Registro.Organizacion
// Seleccione los temas relevantes a su organización.
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appfrisaahorasi.pantallas.InicioSesion.ClickableBox

@Preview
@Composable
fun registroSC5() {

    val tags: List<String> = listOf(
        "Ambientalistas",
        "Artisticos",
        "Ambientalistas",
        "Discapacidades",
        "Medicos"

    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(53.dp),
        verticalArrangement = Arrangement.Top, // Align items to the top
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = "Tags",
            fontSize = 40.sp, // Adjust the size as needed
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(text = "Seleccione los temas relevantes a su organización.",
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 15.sp)
        Spacer(modifier = Modifier.height(44.dp))


        LazyColumn {
            items(items = tags) {
                ClickableBox(tags = it)
            }
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
                .padding(start = 18.dp, top = 25.dp, end = 18.dp, bottom = 6.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp,
                hoveredElevation = 4.dp,
                focusedElevation = 4.dp

            ),
        ) {
            Text(text = "Guardar", color = Color.White)
        }



    }


}