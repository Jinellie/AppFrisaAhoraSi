package com.example.appfrisaahorasi.pantallas.Busqueda

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.appfrisaahorasi.MainActivity
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.navigation.NavRoutes

import androidx.compose.material.Scaffold
import androidx.compose.material.DrawerState
import androidx.compose.material.FabPosition
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import com.example.appfrisaahorasi.pantallas.AppDrawer
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HistorialBrusqre(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(userType = "Personal", navController)
        },
        content = { padding ->
            Content(
                navController,
                modifier = Modifier.padding(padding),
                onMenuClick = {
                    coroutineScope.launch {
                        if (scaffoldState.drawerState.isOpen) {
                            scaffoldState.drawerState.close()
                        } else {
                            scaffoldState.drawerState.open()
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun Content(navController: NavController, modifier: Modifier, onMenuClick: () -> Unit) {

    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.historial_de_busqueda, null)

            val searchButton = view.findViewById<ImageView>(R.id.searchButton)
            val searchEditText = view.findViewById<EditText>(R.id.searchEditText)
            val historialLayout = view.findViewById<LinearLayout>(R.id.historialLayout)

            searchButton.setOnClickListener {
                val searchTerm = searchEditText.text.toString()

                if (searchTerm.isNotEmpty()) {


                    // Crea un nuevo LinearLayout que tendrá un fondo y contendrá el TextView
                    val newSearchLayout = LinearLayout(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,  // Anchura ajustada al contenido
                            LinearLayout.LayoutParams.WRAP_CONTENT   // Altura ajustada al contenido
                        ).apply {
                            // Esto establecerá un margen alrededor de cada LinearLayout.
                            // Por ejemplo, agregar un margen de 10dp en la parte inferior para separar cada LinearLayout
                            setMargins(30, 30, 0, 0) // Convertimos 10dp a píxeles para el margen
                        }
                        setPadding(30, 10, 30, 10)  // Convertimos 10dp a píxeles para el padding

                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER // Para centrar el TextView dentro del LinearLayout
                        setBackgroundResource(R.drawable.inicio_de_sesion_fondoarriba)
                        backgroundTintList = ColorStateList.valueOf(android.graphics.Color.parseColor("#FFFFFF"))
                        isClickable = true
                        isFocusable = true
                        setOnClickListener {
                            // Lógica para cuando se presione el LinearLayout
                            searchEditText.setText(searchTerm)
                        }
                    }

                    // Crea un nuevo TextView que mostrará el término de búsqueda
                    val newSearchText = TextView(context).apply {
                        text = searchTerm
                        textSize = 14f
                        setTypeface(null, Typeface.NORMAL)
                        setTextColor(android.graphics.Color.parseColor("#333333"))
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            topMargin = (0 * context.resources.displayMetrics.density).toInt() // Convertir dp a pixels
                            leftMargin = (0 * context.resources.displayMetrics.density).toInt() // Convertir dp a pixels
                        }
                        layoutParams = params
                    }



                    // Agrega el TextView al LinearLayout
                    newSearchLayout.addView(newSearchText)

                    // Agrega el LinearLayout al LinearLayout principal
                    historialLayout.addView(newSearchLayout)

                    // Limpia el TextInputEditText
                    searchEditText.text?.clear()
                }
            }


            // ACTIVAR EL NAVDRAWER
            val tresBarrasButton = view.findViewById<ImageView>(R.id.tresBarras)
            tresBarrasButton.setOnClickListener {
                onMenuClick()  // Esto abrirá o cerrará el cajón
            }

            view
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Set the background color here
    )
}


@Composable
fun RegresarHistorial()
{

}