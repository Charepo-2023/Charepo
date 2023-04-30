package com.example.charepo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CharacterDetailPublic:AppCompatActivity() {

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
        publicSwitch.visibility = View.GONE
        val editButton = findViewById<Button>(R.id.edit_button)
        editButton.visibility = View.GONE


//
        val charName = character.name.toString()
        val charDescription = character.characterDescription.toString()
        val charBirthday = character.birthday.toString()
        val charGender = character.gender.toString()
        val charRace = character.race.toString()
        val charImages = character.characterImages as ArrayList<Uri>


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