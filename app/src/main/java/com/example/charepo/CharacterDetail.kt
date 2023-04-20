package com.example.charepo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CharacterDetail :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_details)

        val characterNameView = findViewById<TextView>(R.id.character_name)
        val characterDescriptionView = findViewById<TextView>(R.id.description)
        val charName = intent.getStringExtra("Name")
        val charDescription = intent.getStringExtra("Description")

        characterNameView.text = charName
        characterDescriptionView.text = charDescription
    }

}