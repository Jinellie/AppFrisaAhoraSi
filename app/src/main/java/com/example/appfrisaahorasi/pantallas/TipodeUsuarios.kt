package com.example.appfrisaahorasi.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun TipodeUsuario () {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(53.dp),
        verticalArrangement = Arrangement.Top, // Align items to the top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Elige tu tipo de usuario",
            fontSize = 45.sp, // Adjust the size as needed
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(44.dp))


        Personal()

        Spacer(modifier = Modifier.height(30.dp))

        OrgCivil()







    }
}


@Composable
fun Personal(){
    Box(
        modifier = Modifier
            .height(174.dp)
            .width(275.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(
                width = 1.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Reduced padding
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                modifier = Modifier
                    .size(115.dp)
            )

            Spacer(modifier = Modifier.height(5.dp)) // Added spacer for space

            Text(
                text = "Personal",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}


@Composable
fun OrgCivil(){
    Box(
        modifier = Modifier
            .height(174.dp)
            .width(275.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(
                width = 1.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Reduced padding
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                modifier = Modifier
                    .size(115.dp)
            )

            Spacer(modifier = Modifier.height(5.dp)) // Added spacer for space

            Text(
                text = "Organizacion Civil",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}