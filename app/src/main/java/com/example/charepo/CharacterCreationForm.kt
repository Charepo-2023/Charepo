package com.example.charepo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CharacterCreationForm :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_creation)

        val submitBtn = findViewById<Button>(R.id.submit_btn)
        submitBtn.setOnClickListener {
            this.finish()
        }
    }
}