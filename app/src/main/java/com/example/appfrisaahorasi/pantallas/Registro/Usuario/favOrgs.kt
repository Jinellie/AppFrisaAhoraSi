package com.example.appfrisaahorasi.pantallas.Registro.Usuario
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.appfrisaahorasi.R
import com.example.appfrisaahorasi.navigation.NavRoutes


@Composable
fun favOrgs(navController: NavController) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top, // Align items to the top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(
            factory = {context->
                val view=LayoutInflater.from(context).inflate(R.layout.activity_main,null)
                val searchBar = view.findViewById<LinearLayout>(R.id.searchBar)
                searchBar.setOnClickListener {
                    navController.navigate(NavRoutes.busquedaTags)
                }
                view

            },
            modifier=Modifier
                .fillMaxWidth()
        )
        AndroidView(
            factory = {context->
                val view=LayoutInflater.from(context).inflate(R.layout.favs_osc,null)
                val favPubs =view.findViewById<LinearLayout>(R.id.favsPubs)
                favPubs.setOnClickListener{
                    navController.navigate(NavRoutes.favoritos)
                }
                val orgs=view.findViewById<LinearLayout>(R.id.Orgs)
                orgs.setOnClickListener{
                    navController.navigate(NavRoutes.favOrgs)
                }

                view
            },
            modifier=Modifier
                .fillMaxWidth()
        )
    }
}
