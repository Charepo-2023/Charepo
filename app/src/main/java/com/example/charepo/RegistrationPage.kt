package com.example.charepo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationPage : AppCompatActivity() {

    lateinit var  db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrationpage)

        db = DBHelper(this)

        var email = findViewById<EditText>(R.id.emailInput).text
        var email2 = email.toString()
        val password = findViewById<EditText>(R.id.passwordInput).text
        val password2 = password.toString()
        val confirmPassword = findViewById<EditText>(R.id.confirmPasswordInput).text
        val confirmPassword2 = confirmPassword.toString()
        val btn = findViewById<Button>(R.id.registerBtn)

        btn.setOnClickListener {
            if(password2.compareTo(confirmPassword2) == 0)
            {
                db.insertdate(email2,password2)
                Toast.makeText(applicationContext, "Logged In",Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext, password,Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext, confirmPassword,Toast.LENGTH_SHORT).show()

            }
            else
            {
                Toast.makeText(applicationContext, "Passwords don't match",Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun OpenLogin(){
        //val intent = Intent(this@RegistrationPage, LoginPage::class.java)
        //startActivity(intent)
    }
}