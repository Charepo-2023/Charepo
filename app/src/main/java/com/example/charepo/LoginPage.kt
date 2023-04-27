package com.example.charepo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {
    lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpage)

        var emailInput = findViewById<EditText>(R.id.email).text
        var emailInput2 = emailInput.toString()
        var password = findViewById<EditText>(R.id.password).text
        var password2 = password.toString()
        var btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            db = DBHelper(this)
            if(db.checkUserPassword(emailInput2,password2))
            {
                Toast.makeText(applicationContext, "Logged In", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(applicationContext, "Wrong User Name and Password",Toast.LENGTH_SHORT).show()
            }

        }
    }
}