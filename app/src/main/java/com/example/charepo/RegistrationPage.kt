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


        val btn = findViewById<Button>(R.id.registerBtn)

        btn.setOnClickListener {
            var userName = findViewById<EditText>(R.id.userNameInput).text.toString()
            val password = findViewById<EditText>(R.id.passwordInput).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.confirmPasswordInput).text.toString()

            if(password.compareTo(confirmPassword) == 0)
            {
                db.insertdate(userName,password)
                Toast.makeText(applicationContext, "Logged In",Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext, password,Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext, confirmPassword,Toast.LENGTH_SHORT).show()
                this.finish()
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