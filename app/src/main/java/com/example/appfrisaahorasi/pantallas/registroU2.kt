package com.example.appfrisaahorasi.pantallas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RegistroU2() {

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

        Text(text = "Seleccione los temas con los cuales desea que le aparezca informacion relevante.",
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 15.sp)
        Spacer(modifier = Modifier.height(44.dp))


        LazyColumn {
            items(items = tags) {
                ExampleBox(tags = it)
            }
        }





    }


}

//@Preview
@Composable
fun ExampleBox(tags: String){

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center).padding(15.dp)) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(275.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(
                    width = 1.5.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(15.dp))
                .background(Color.White)




        )
        {
            Text(text = tags,
                modifier = Modifier.padding(16.dp)
                .fillMaxSize(),
                textAlign = TextAlign.Center,
                color = Color.Black)

        }
    }
}