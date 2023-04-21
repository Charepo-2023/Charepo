package com.example.charepo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class CharacterCreationForm :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_creation)

        val submitBtn = findViewById<Button>(R.id.submit_btn)
        val genderDropDown = findViewById<Spinner>(R.id.gender_dropdown)
        val dropDownAdapter = ArrayAdapter.createFromResource(this,R.array.genders,R.layout.spinner_item)
        dropDownAdapter.setDropDownViewResource(R.layout.spinner_item)
        genderDropDown.adapter = dropDownAdapter


        submitBtn.setOnClickListener {
            this.finish()
        }
    }
}