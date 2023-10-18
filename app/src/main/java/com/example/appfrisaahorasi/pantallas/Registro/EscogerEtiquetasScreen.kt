
package com.example.appfrisaahorasi.pantallas.Registro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun EscogerEtiquetasScreen(navController: NavController, tTagsViewModel: TagsViewModel = viewModel()) {
    val tags = tTagsViewModel.tagsList.value

    // Store temporaly the tags to subbmit at the end
    val selectedTags = remember { mutableStateListOf<String>() }

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
        Spacer(modifier = Modifier.height(34.dp))
        // Envolver el LazyColumn con un Box para darle un peso
        Box(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn {
                items(items = tags) {
                    ClickableBox(it, selectedTags)
                }
            }
        }

        Button(
            onClick = {tTagsViewModel.uploadDataTags(navController,selectedTags, NavigationFlow.REGISTRATION_USER)
            },
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


//@Preview
@Composable
fun ClickableBox(tags: String, selectedTags: MutableList<String>) {
    var isClicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(275.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(
                    width = 1.5.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(15.dp)
                )
                .background(if (isClicked) Color.Black else Color.White)
                .clickable {
                    isClicked = !isClicked
                    if (isClicked) {
                        selectedTags.add(tags)
                    } else {
                        selectedTags.remove(tags)
                    }
                }
        ) {
            Text(
                text = tags,
                modifier = Modifier.padding(16.dp).fillMaxSize(),
                textAlign = TextAlign.Center,
                color = if (isClicked) Color.White else Color.Black
            )
        }
    }
}





