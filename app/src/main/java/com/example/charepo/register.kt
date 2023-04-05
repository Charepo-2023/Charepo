package com.example.charepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        val username=findViewById<EditText>(R.id.username).text
        val password=findViewById<EditText>(R.id.Password).text
        val password2=findViewById<EditText>(R.id.PasswordRepeat).text
        val signUp=findViewById<Button>(R.id.signUp)

        //List for usernames and passwords
        val userList= ArrayList<String>()
        val passList=ArrayList<String>()

        signUp.setOnClickListener(){
            val user=username.toString()
            val pass=password.toString()
            val pass2=password2.toString()
            Log.v("Username",user)
            Log.v("Password",pass)
            Log.v("Password Re-Entered",pass2)
            //If username is blank
            if(user==""){
                Log.v("Username Error","Username is blank")
                Toast.makeText(this, "Enter a username", Toast.LENGTH_SHORT).show()
            }
            //If password is blank
            else if(pass==""){
                Log.v("Password Error","Password is blank")
                Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show()
            }
            //If repeated passord is blank
            else if(pass2==""){
                Log.v("Password Error","Password2 is blank")
                Toast.makeText(this, "Re-enter password to verify", Toast.LENGTH_SHORT).show()
            }
            //if the passwords are not the same
            else if (pass!=pass2){
                Log.v("Password Error","Passwords don't match")
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            }
            //If an account is already made by this user
            else if(userList.contains(user)){
                Log.v("Username Error","User Already exists")
                Toast.makeText(this, "User already exists, enter a different username.", Toast.LENGTH_SHORT).show()
            }
            //Create account
            else{
                Log.v("Verified","Username and Password Verified")
                userList+=user
                passList+=pass
                Log.v("User List",userList.toString())
                Log.v("Password list",passList.toString())
                Toast.makeText(this, "You are now registered.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}