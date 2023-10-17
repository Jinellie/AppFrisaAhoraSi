package com.example.appfrisaahorasi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var phoneNumberEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingresar_inicio_de_sesion)

        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        val loginButton: LinearLayout = findViewById(R.id.btnLogin)
        loginButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString()
            val password = passwordEditText.text.toString()

            val intent = Intent(this, ComposeLoginActivity::class.java)

            intent.putExtra("phoneNumber", phoneNumber)
            intent.putExtra("password", password)
            startActivity(intent)

        }
    }
}
