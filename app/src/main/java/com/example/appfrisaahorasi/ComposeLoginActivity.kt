package com.example.appfrisaahorasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.appfrisaahorasi.ui.theme.RedApp
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

@Composable
fun ComposeLoginActivity(
    phoneNumber: String?,
    password: String?,
    onVerificationCodeEntered: (String) -> Unit // Callback to handle entered verification code
) {
    // Your Composable UI code here
    // You can display the login UI and handle navigation


    @Composable
    fun CodeInputPopup(onVerificationCodeEntered: (String) -> Unit) {
         val openAlertDialog = remember { mutableStateOf(true) }
    when {

           openAlertDialog.value -> {
        // Define a callback function to capture the codigo value
        var codigo: String = ""
        val codigoCallback: (String) -> Unit = { value -> codigo = value }
        AlertDialogExample(
            isVisible = true,

            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = { codigo: String ->
                openAlertDialog.value = false
                println("Confirmation registered") // Add logic here to handle confirmation.
                codigoCallback(codigo)
            },
            dialogTitle = "Ingresar código",

            icon = Icons.Default.Info,
            codigoCallback = codigoCallback
        )
         }
    }
        // Call CodeInputPopup and pass the callback
        CodeInputPopup(onVerificationCodeEntered)

    // Handle UI and navigation based on your logic
    // For example, if verification is successful, navigate to the next screen
}
}

class ComposeLoginActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    val phoneNumber = intent.getStringExtra("phoneNumber")
    val password = intent.getStringExtra("password")

    // MutableState for controlling the code input popup
    var isCodeInputPopupVisible by mutableStateOf(false)
    var verificationCode by mutableStateOf("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]

        if (phoneNumber != null) {
            startPhoneNumberVerification(phoneNumber)
        }

        setContent {
            val phoneNumber = intent.getStringExtra("phoneNumber")
            val password = intent.getStringExtra("password")

            ComposeLoginActivity(phoneNumber, password) { enteredCode ->
                // Handle the entered code, e.g., sign in with it
                // This callback is invoked when the confirmation button is clicked in the popup
                verifyPhoneNumberWithCode(storedVerificationId, enteredCode)
            }
        }


        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.d(TAG, "FirebaseAuthInvalidCredentialsException")
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Log.d(TAG, "FirebaseTooManyRequestsException")
                } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                    // reCAPTCHA verification attempted with null Activity
                    Log.d(TAG, "FirebaseAuthMissingActivityForRecaptchaException")
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token



            }
        }
        // [END phone_auth_callbacks]
    }

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser //TODO: continuar login
        updateUI(currentUser)
    }
    // [END on_start_check_user]

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        // [END verify_with_code]
    }

    // [START resend_verification]
    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?,
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // (optional) Activity for callback binding
            // If no activity is passed, reCAPTCHA verification can not be used.
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }
    // [END resend_verification]

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    //val currentUser = auth.currentUser
                    updateUI(user)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        val errorMessage =
                            task.exception?.message ?: "Error en el inicio de sesión."
                        showToast(errorMessage)
                    }
                    // Update UI
                }
            }
    }
    // [END sign_in_with_phone]

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateUI(user: FirebaseUser? = auth.currentUser) {

        val db = FirebaseFirestore.getInstance()
        val userId = Firebase.auth.currentUser?.uid // Get the user's ID

        if (userId != null) {
            val userDocument = db.collection("Users").document(userId)

            userDocument.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userData = documentSnapshot.data
                        val tipoUsuario = userData?.get("tipoUsuario") as String?
                        if (tipoUsuario != null) {
                            // You now have the "tipoUsuario" attribute
                            val intent = Intent(this, HomeActivity::class.java)
                            intent.putExtra("tipoUsuario", tipoUsuario)
                            startActivity(intent)
                        } else {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            // The "tipoUsuario" attribute is not set in the database
                        }
                    } else {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        // User document doesn't exist
                    }
                }
                .addOnFailureListener { e ->
                    // Handle any errors
                }
        }
    }

    companion object {
        private const val TAG = "ComposeLoginActivity"
    }


}


@Composable
fun AlertDialogExample(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
    dialogTitle: String,
    icon: ImageVector,
    codigoCallback: (String) -> Unit // Callback to receive the codigo value

) {
    var codigo by remember { mutableStateOf("") }
    if (isVisible) {
    AlertDialog(

        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            TextField(value = "", onValueChange = { codigo = it })
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(codigo)
                }
            ) {
                Text("Confirmar", color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancelar", color = RedApp)
            }
        },
        containerColor = Color.White,
        textContentColor = Color.DarkGray,
        titleContentColor = Color.Black
    )
}
}


