package com.example.appfrisaahorasi.pantallas

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.appfrisaahorasi.R
import kotlinx.coroutines.launch

@Composable
fun Home(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(userType = "Personal", navController)
        },
        content = { padding ->
            ContentHome(
               // navController,
                modifier = Modifier.padding(padding).clickable { navController.navigate("perfilOrgvistaUsuario") },
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
fun ContentHome(modifier: Modifier, onMenuClick: () -> Unit) {
    lateinit var navController: NavHostController

    navController = rememberNavController()

    val  listaDeOSC = listOf(
        User(
            userName = "Diez por Ciénega A.C.",
            imageUrl = "https://picsum.photos/id/0/200",
            content = "¡Estamos emocionados de anunciar nuestro próximo evento comunitario! 🎉 Únetenos el 15 de noviembre a las 10:00 AM en el Parque Central para una jornada llena de actividades, música en vivo y diversión para toda la familia. No te lo pierdas. ¡Te esperamos!",
            contentImageUrl = "https://picsum.photos/id/12/200"
        ),
        User(
            userName = "Organización Solidaria A.B.",
            imageUrl = "https://picsum.photos/id/1/200",
            content = "Hoy celebramos un año de logros y trabajo en equipo. Gracias a todos nuestros colaboradores y voluntarios por hacer posible nuestro impacto en la comunidad. #Aniversario #Gracias",
            contentImageUrl = "https://picsum.photos/id/22/200"
        ),
        User(
            userName = "Fundación Esperanza X.Y.Z.",
            imageUrl = "https://picsum.photos/id/2/200",
            content = "Nuestra misión es brindar oportunidades educativas a niños desfavorecidos. ¡Acompáñanos en nuestra próxima campaña de donación! Juntos podemos hacer una diferencia.",
            contentImageUrl = "https://picsum.photos/id/32/200"
        ),
        User(
            userName = "Asociación Pro Medio Ambiente",
            imageUrl = "https://picsum.photos/id/3/200",
            content = "Hoy plantamos 1000 árboles en el Parque Ecológico. ¡Gracias a nuestros voluntarios y patrocinadores por su apoyo continuo a la conservación del medio ambiente!",
            contentImageUrl = "https://picsum.photos/id/42/200"
        ),
        User(
            userName = "Acción Humanitaria B.C.D.",
            imageUrl = "https://picsum.photos/id/4/200",
            content = "Estamos comprometidos con la ayuda humanitaria en todo el mundo. Únete a nosotros en nuestra próxima misión de ayuda a refugiados.",
            contentImageUrl = "https://picsum.photos/id/52/200"
        ),
        User(
            userName = "Fundación Sonrisas E.F.G.",
            imageUrl = "https://picsum.photos/id/5/200",
            content = "Ayuda a hacer sonreír a un niño hoy. Dona juguetes y libros para nuestra campaña de regalos de Navidad. #Sonrisas",
            contentImageUrl = "https://picsum.photos/200"
        ),
        User(
            userName = "Ayuda para Personas Mayores H.I.J.",
            imageUrl = "https://picsum.photos/id/6/200",
            content = "En el Día del Abuelo, honramos a nuestros queridos ancianos y les agradecemos por su sabiduría y amor. ¡Feliz Día del Abuelo!",
            contentImageUrl = "https://picsum.photos/200"
        ),
        User(
            userName = "Fundación Educación K.L.M.",
            imageUrl = "https://picsum.photos/id/7/200",
            content = "La educación es la clave para un futuro brillante. Únete a nuestra campaña de becas para apoyar a estudiantes talentosos y motivados.",
            contentImageUrl = "https://picsum.photos/200"
        ),
        User(
            userName = "Organización Cultural N.O.P.",
            imageUrl = "https://picsum.photos/id/8/200",
            content = "Explora el arte y la cultura con nosotros. ¡Visita nuestro museo y disfruta de exposiciones fascinantes y eventos culturales!",
            contentImageUrl = "https://picsum.photos/200"
        ),
        User(
            userName = "Fundación de Salud Q.R.S.",
            imageUrl = "https://picsum.photos/id/9/200",
            content = "Cuida tu salud y bienestar. Acompáñanos en nuestro seminario web gratuito sobre hábitos saludables y prevención de enfermedades.",
            contentImageUrl = "https://picsum.photos/200"
        )
    )
    LazyColumnWithRoundedCards(users = listaDeOSC)
    AndroidView(
        factory = { context ->
            // Inflate your XML layout using the context
            val view = LayoutInflater.from(context).inflate(R.layout.activity_main, null)

            // ACTIVAR EL NAVDRAWER
            val tresBarrasButton = view.findViewById<ImageView>(R.id.toolbar_icon)
            tresBarrasButton.setOnClickListener {
                onMenuClick()  // Esto abrirá o cerrará el cajón
            }

            view
        },
        modifier = Modifier.fillMaxSize()
            //.background(Color.White) // Set the background color here

    )
}

data class User(val userName: String, val imageUrl: String, val content: String, val contentImageUrl: String)

@Composable
fun RoundedCard(user: User) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imagePainter = rememberAsyncImagePainter(model = user.imageUrl)
                Image(
                    painter = imagePainter,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = user.userName, fontSize = 16.sp)
            }

            // Body (You can use either an Image or Text)
            Text(text = user.content, fontSize = 14.sp, modifier = Modifier.padding(10.dp))
            val contentImagePainter = rememberAsyncImagePainter(model = user.contentImageUrl)
            Image(
                painter = contentImagePainter,
                contentDescription = "Content Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FooterIcon(icon = Icons.Default.Favorite, text = "Like")
                FooterIcon(icon = Icons.Default.Comment, text = "Comment")
            }
        }
    }
}

@Composable
fun FooterIcon(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Text(text = text)
    }
}

@Composable
fun LazyColumnWithRoundedCards(users: List<User>) {
    LazyColumn (contentPadding = PaddingValues(horizontal = 0.dp, vertical = 50.dp)) {

        items(users) { user ->
            RoundedCard(user = user)
        }
    }
}

