package com.example.appfrisaahorasi.pantallas.Registro.Organizacion

// firebase y phone auth registro
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appfrisaahorasi.pantallas.Registro.RegistroViewModel
import com.example.appfrisaahorasi.ui.theme.RedApp


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(red = 0.37578123807907104f, green = 0.37578123807907104f, blue = 0.37578123807907104f, alpha = 0.20999999344348907f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            placeholderColor = Color.Black,
            disabledPlaceholderColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .padding(18.dp, 6.dp, 18.dp, 6.dp),
        shape = RoundedCornerShape(5.dp),
        singleLine = true,
        textStyle = TextStyle.Default,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onDone = { /* Acción al presionar Enter/Done */ })
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    isPassword: Boolean = false,
    passwordVisibility: Boolean = true
) {
    val visualTransformation = if (isPassword) {
        if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None
    } else VisualTransformation.None

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(red = 0.37578123807907104f, green = 0.37578123807907104f, blue = 0.37578123807907104f, alpha = 0.20999999344348907f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            placeholderColor = Color.Black,
            disabledPlaceholderColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .padding(18.dp, 0.dp, 18.dp, 6.dp),
        shape = RoundedCornerShape(5.dp),
        singleLine = true,
        textStyle = TextStyle.Default,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onDone = { /* Acción al presionar Enter/Done */ }),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { /* Toggle password visibility */ }) {
                    val visibilityIcon =
                        if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    val description = if (passwordVisibility) "Show password" else "Hide password"
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistroScreen(navController: NavController,viewModel: RegistroViewModel) {
    Scaffold(
        // nav bar iría aquí
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Registro",
                    fontSize = 40.sp, // Adjust the size as needed
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(text = "Ingrese sus datos en los siguientes campos",
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 15.sp)
                Text(
                    text = "Obligatorio *",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(18.dp, 6.dp, 18.dp)
                        .width(77.dp)
                        //.height(18.dp)
                        .alpha(1f),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )
                CustomTextField(
                    value = viewModel.nombre,
                    onValueChange = { newValue -> viewModel.onNombreChanged(newValue)},
                    placeholder = "Nombre del encargado",
                    keyboardType = KeyboardType.Text
                )
                Text(
                    text = "Obligatorio *",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(18.dp, 6.dp, 18.dp)
                        .width(77.dp)
                        //.height(18.dp)
                        .alpha(1f),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )

                CustomTextField(
                    value = viewModel.celular,
                    onValueChange = { newValue -> viewModel.onCelularChanged(newValue) },
                    placeholder = "Celular",
                    keyboardType = KeyboardType.Phone
                )

                var passwordHidden by rememberSaveable { mutableStateOf(true) }
                Text(
                    text = "Obligatorio *",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(18.dp, 6.dp, 18.dp)
                        .width(77.dp)
                        //.height(18.dp)
                        .alpha(1f),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )
                PasswordTextField(
                    value = viewModel.contrasena,
                    onValueChange = { newValue -> viewModel.onContrasenaChanged(newValue) },
                    placeholder = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    passwordVisibility = passwordHidden
                )


                Button(
                    onClick = { viewModel.validateContinue1(navController)},
                    shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(red = 0.7216145992279053f, green = 0.015033637173473835f, blue = 0.015033637173473835f, alpha = 0.7900000214576721f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(275.dp)
                        .padding(start = 18.dp, top = 6.dp, end = 18.dp, bottom = 6.dp),
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
    )
    if (viewModel.showErrorDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.showErrorDialog = false }, // Esto se invocará cuando se toque fuera del diálogo
            title = { Text(text = "Error") },
            text = { Text(text = viewModel.dialogMessage) },
            buttons = {
                Button(onClick = { viewModel.showErrorDialog = false }, shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = RedApp), modifier = Modifier.fillMaxWidth() // FIXME MAYBE
                    ) { // Esto se invocará cuando se presione el botón
                    Text(text = "Aceptar", color = Color.White)
                }
            }
        )
    }
}


// TODOS


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidationExample() {
    var textInput by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }

    TextField(
        value = textInput,
        onValueChange = { input ->
            textInput = input
            isValid = input.isNotEmpty() // Add your custom validation rules here
        },
        label = { Text("Enter Text") },
        isError = !isValid
    )

    if (!isValid) {
        Text(text = "Este campo es obligatorio", color = Color.Red)
    }
}

