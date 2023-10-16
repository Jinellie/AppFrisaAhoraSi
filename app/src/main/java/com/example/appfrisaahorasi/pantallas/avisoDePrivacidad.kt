package com.example.appfrisaahorasi.pantallas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AvisodePrivacidadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvisodePrivacidadScreen()
        }
    }
}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AvisodePrivacidadScreen() {

    Scaffold( // nav bar iría aquí
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                backgroundColor = Color(red = 0.7216145992279053f, green = 0.015033637173473835f, blue = 0.015033637173473835f, alpha = 0.7900000214576721f)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Aviso de Privacidad",
                    fontSize = 40.sp, // Adjust the size as needed
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(text = "Fecha de última actualización: 12/10/23\n" +
                        "\n" +
                        "Este Aviso de Privacidad describe cómo esta app recopila, utiliza y protege la información personal que proporcionas a través de nuestra aplicación móvil. Al usar nuestra aplicación, aceptas los términos de este Aviso de Privacidad.\n" +
                        "\n" +
                        "1. Información que recopilamos\n" +
                        "\n" +
                        "Podemos recopilar la siguiente información personal cuando utilizas nuestra aplicación:\n" +
                        "\n" +
                        "Información de registro: Esto puede incluir tu nombre, dirección de correo electrónico, nombre de usuario y contraseña.\n" +
                        "\n" +
                        "Información de perfil: Puedes proporcionar información adicional en tu perfil, como una foto de perfil y detalles personales opcionales.\n" +
                        "\n" +
                        "Información de uso: Recopilamos información sobre cómo utilizas la aplicación, como las funciones que utilizas y la interacción con otros usuarios.\n" +
                        "\n" +
                        "Información de ubicación: Si habilitas los servicios de ubicación en tu dispositivo móvil, podemos recopilar información sobre tu ubicación geográfica.\n" +
                        "\n" +
                        "2. Uso de la información\n" +
                        "\n" +
                        "Utilizamos la información personal que recopilamos para los siguientes propósitos:\n" +
                        "\n" +
                        "Proporcionar y mantener la funcionalidad de la aplicación.\n" +
                        "\n" +
                        "Personalizar tu experiencia en la aplicación y mostrar contenido relevante.\n" +
                        "\n" +
                        "Comunicarnos contigo, como para enviar notificaciones y actualizaciones.\n" +
                        "\n" +
                        "Mejorar nuestros productos y servicios.\n" +
                        "\n" +
                        "Cumplir con las leyes y regulaciones aplicables.\n" +
                        "\n" +
                        "3. Compartir la información\n" +
                        "\n" +
                        "Podemos compartir tu información personal en las siguientes circunstancias:\n" +
                        "\n" +
                        "Con otros usuarios de la aplicación, en función de tus preferencias de privacidad.\n" +
                        "\n" +
                        "Con terceros proveedores de servicios que nos ayudan a operar y mantener la aplicación.\n" +
                        "\n" +
                        "Cuando lo exija la ley o en respuesta a una solicitud legal válida.\n" +
                        "\n" +
                        "4. Seguridad de la información\n" +
                        "\n" +
                        "Tomamos medidas razonables para proteger tu información personal de acceso no autorizado, divulgación, alteración o destrucción. Sin embargo, ten en cuenta que ninguna medida de seguridad es 100% segura.\n" +
                        "\n" +
                        "5. Tus derechos\n" +
                        "\n" +
                        "Tienes derechos en relación con tus datos personales, incluyendo el acceso, corrección y eliminación de tus datos. Puedes ejercer estos derechos contactándonos a través de appfrisa@gmail.com.\n" +
                        "\n" +
                        "6. Cambios en el Aviso de Privacidad\n" +
                        "\n" +
                        "Podemos actualizar este Aviso de Privacidad en el futuro. Te notificaremos sobre cambios significativos a través de la aplicación o por otros medios apropiados. Te recomendamos que revises este Aviso de Privacidad periódicamente.\n" +
                        "\n" +
                        "7. Contacto\n" +
                        "\n" +
                        "Si tienes preguntas o inquietudes sobre este Aviso de Privacidad, o si deseas ejercer tus derechos relacionados con tus datos personales, puedes contactarnos a través de appfrisa@gmail.com.\n" +
                        "\n" +
                        "Este Aviso de Privacidad es efectivo a partir de la fecha de última actualización indicada arriba.",
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Start,
                    color = Color.DarkGray,
                    fontSize = 15.sp)

                var checked by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier.selectableGroup(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { isChecked ->
                            checked = isChecked
                        }
                    )

                    Text("Aceptar aviso de privacidad")
                }



            }
        }
    )
}