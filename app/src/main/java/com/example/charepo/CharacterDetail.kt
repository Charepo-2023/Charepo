package com.example.charepo

import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
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

//        val charName = intent.getStringExtra("Name")
//        val charDescription = intent.getStringExtra("Description")
//        val charBirthday = intent.getStringExtra("Birthday")
//        val charGender = intent.getStringExtra("Gender")
//        val charRace = intent.getStringExtra("Race")
//        val isPublic = intent.getIntExtra("Public",0)
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU ) {
//             charImages = (intent.getParcelableArrayListExtra("Images", ArrayList<Uri>()::class.java) as? ArrayList<Uri>)!!
//        }else{
//            charImages = intent.getParcelableArrayListExtra("Images")!!
//        }
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

        val imageRecyclerView = findViewById<RecyclerView>(R.id.imageRV)
        val imageAdapter = CharacterDetailAdapter(this,charImages)
        imageRecyclerView.adapter = imageAdapter
        imageRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

    }


}