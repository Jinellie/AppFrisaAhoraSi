package com.example.appfrisaahorasi.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appfrisaahorasi.ui.theme.RedApp

@Composable
fun AppDrawer(userType: String) {
    // Define the selected drawer content Composable
    val drawerContent: @Composable () -> Unit = when (userType) {
        "OSC" -> { { OSCDrawerContent() } }
        "Personal" -> { { StandardUserDrawerContent() } }
        else -> { { InvitadoDrawerContent() } }
    }

    // Display the selected drawer content Composable
    Column {
        drawerContent()
    }
}

@Preview
@Composable
fun OSCDrawerContent() {
    // Column Composable
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Navigation button with icon
        NavigationButton(
            text = "Home",
            icon = Icons.Default.Home, // Replace with your desired icon
            onClick = { /* Handle click */ }
        )

        NavigationButton(
            text = "Profile",
            icon = Icons.Default.Person, // Replace with your desired icon
            onClick = { /* Handle click */ }
        )

        // Add more navigation buttons as needed
    }
}

@Composable
fun StandardUserDrawerContent() {
    // Column Composable
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Navigation button with icon
        NavigationButton(
            text = "Home",
            icon = Icons.Default.Home, // Replace with your desired icon
            onClick = { /* Handle click */ }
        )

        NavigationButton(
            text = "Profile",
            icon = Icons.Default.Person, // Replace with your desired icon
            onClick = { /* Handle click */ }
        )

        // Add more navigation buttons as needed
    }
}

@Composable
fun InvitadoDrawerContent() {
    // Column Composable
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Navigation button with icon
        NavigationButton(
            text = "Home",
            icon = Icons.Default.Home, // Replace with your desired icon
            onClick = { /* Handle click */ }
        )

        NavigationButton(
            text = "Profile",
            icon = Icons.Default.Person, // Replace with your desired icon
            onClick = { /* Handle click */ }
        )

        // Add more navigation buttons as needed
    }
}

@Composable
fun NavigationButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null, // Optional content description
            modifier = Modifier.size(24.dp), // Icon size
            tint = Color.Black
        )
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            color = Color.Black
        )
    }
}


@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    // TopAppBar Composable
    TopAppBar(
        title = {
            Text(text = "Search...", color = Color.White)
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.clickable {
                    onMenuClicked()
                },
                tint = Color.White
            )
        },
        backgroundColor = RedApp
    )
}