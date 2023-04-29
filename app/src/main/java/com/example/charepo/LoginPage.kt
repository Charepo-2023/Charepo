package com.example.charepo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {
    lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpage)

        var btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            var username = findViewById<EditText>(R.id.username).text.toString()
            var password = findViewById<EditText>(R.id.password).text.toString()
            db = DBHelper(this)
            if(db.checkUserPassword(username,password))
            {
                LoginTracker.setCurrentUserName(username)
                LoginTracker.setUserLoggedInVal(true)
                Toast.makeText(applicationContext, "Logged In", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                )
                startActivity(intent)
            }
            else
            {
                Toast.makeText(applicationContext, "Wrong User Name and Password",Toast.LENGTH_SHORT).show()
            }

        }
    }
}