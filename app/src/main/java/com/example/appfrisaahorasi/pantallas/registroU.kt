package com.example.appfrisaahorasi.pantallas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RegistroU() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(53.dp),
        verticalArrangement = Arrangement.Top, // Align items to the top
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = "Registro",
            fontSize = 40.sp, // Adjust the size as needed
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

    }
}