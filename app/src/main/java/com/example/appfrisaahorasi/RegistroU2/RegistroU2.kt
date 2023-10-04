package com.example.appfrisaahorasi.RegistroU2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RegistroU2() {
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

        Text(text = "Seleccione los temas con los cuales desea que le aparezca informacion relevante.",
            modifier = Modifier.padding(15.dp))


        ExampleBox()





    }


}

//@Preview
@Composable
fun ExampleBox(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .background(Color.White)
                .width(275.dp)
                .border(
                    width = 1.5.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(15.dp)
                )

        )
        {
            Text(text = "Ambientalistas",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center)
        }
    }
}