package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var notifError: TextView

    private fun initComponent() {
        loginButton = findViewById(R.id.loginBtn)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        notifError = findViewById(R.id.notifError)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponent()
        loginButton.setOnClickListener {
            val userEmail = "gefila"
            val userPassword = "12345"
            val username = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email atau Password tidak boleh kosong", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (username == userEmail && password == userPassword) {
                Toast.makeText(this, "Selamat Datang $username", Toast.LENGTH_LONG).show()
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Email atau Password Salah", Toast.LENGTH_LONG).show()
            }

        }
    }
}