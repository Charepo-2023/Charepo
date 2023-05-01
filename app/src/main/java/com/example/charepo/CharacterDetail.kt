package com.example.charepo

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CharacterDetail :AppCompatActivity() {
      lateinit var character: HomeRecyclerViewItem.CharacterItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_details)

        character = Fetcher.getCurrentCharacterVal()
        val characterNameView = findViewById<TextView>(R.id.character_name)
        val characterDescriptionView = findViewById<TextView>(R.id.description)
        val characterBirthdayView = findViewById<TextView>(R.id.birthday)
        val characterGenderView = findViewById<TextView>(R.id.gender)
        val characterRaceView = findViewById<TextView>(R.id.race)
        val publicSwitch = findViewById<Switch>(R.id.public_switch)
        val editButton = findViewById<Button>(R.id.edit_button)

        editButton.setOnClickListener {
            Fetcher.setLoadCharacterVal(true)
            val editCharacterForm = CharacterCreationForm()
            val intent = Intent(it.context,editCharacterForm::class.java)
            startActivity(intent)
        }

//
        val charName = character.name.toString()
        val charDescription = character.characterDescription.toString()
        val charBirthday = character.birthday.toString()
        val charGender = character.gender.toString()
        val charRace = character.race.toString()
        val isPublic = character.isPublic
        val charImages = character.characterImages as ArrayList<Uri>


        if(isPublic == 1){
            publicSwitch.isChecked = true
        }

        characterNameView.text = charName
        characterDescriptionView.text = charDescription
        characterBirthdayView.text = charBirthday
        characterGenderView.text = charGender
        characterRaceView.text = charRace


        val defaultImageView = findViewById<ImageView>(R.id.default_image_view)
        val imageRecyclerView = findViewById<RecyclerView>(R.id.imageRV)

        if (charImages.isEmpty()){
            imageRecyclerView.visibility = View.GONE
            defaultImageView.visibility = View.VISIBLE
        }else{
            imageRecyclerView.visibility = View.VISIBLE
            defaultImageView.visibility = View.GONE
            val imageAdapter = CharacterDetailAdapter(this,charImages)
            imageRecyclerView.adapter = imageAdapter
            imageRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        }


    }


}