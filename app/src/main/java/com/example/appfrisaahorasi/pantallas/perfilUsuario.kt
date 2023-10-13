package com.example.appfrisaahorasi.pantallas

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.example.appfrisaahorasi.R

@SuppressLint("InflateParams")
@Preview
@Composable
fun ComposeWithXmlLayout() {
    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.profile_user, null)
            val nombre = view.findViewById<TextView>(R.id.Element2) // Replace with your TextView ID
            val edad = view.findViewById<TextView>(R.id.e3) // Replace with your TextView ID
            val numerotel = view.findViewById<TextView>(R.id.e4) // Replace with your TextView ID
            val desc = view.findViewById<TextView>(R.id.e5) // Replace with your TextView ID
            val tag1 = view.findViewById<TextView>(R.id.e6) // Replace with your TextView ID
            val tag2 = view.findViewById<TextView>(R.id.e7) // Replace with your TextView ID
            val tag3 = view.findViewById<TextView>(R.id.e8) // Replace with your TextView ID
            val tag4 = view.findViewById<TextView>(R.id.e9) // Replace with your TextView ID
            val tag5 = view.findViewById<TextView>(R.id.e10) // Replace with your TextView ID

            val poppins_light = ResourcesCompat.getFont(context, R.font.poppins_light) // Use the Google Fonts font
            val poppins_medium = ResourcesCompat.getFont(context, R.font.poppins_medium) // Use the Google Fonts font
            val poppins_semibold = ResourcesCompat.getFont(context, R.font.poppins_semibold) // Use the Google Fonts font
            nombre.typeface = poppins_semibold
            edad.typeface = poppins_light
            numerotel.typeface = poppins_light
            desc.typeface = poppins_medium
            tag1.typeface = poppins_medium
            tag2.typeface = poppins_medium
            tag3.typeface = poppins_medium
            tag4.typeface = poppins_medium
            tag5.typeface = poppins_medium


            view
        },
        modifier = Modifier.fillMaxSize()
    )
}


