package com.example.charepo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CharacterCreationForm :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_creation)

        val loadCharacter = Fetcher.getLoadCharacterVal()
        //Load all the views and inputs
        val nameInput = findViewById<EditText>(R.id.name_input)
        val raceInput = findViewById<EditText>(R.id.race_input)
        val birthdayInput = findViewById<EditText>(R.id.birthday_input)
        val descriptionInput = findViewById<EditText>(R.id.description_input)
        val isPublicSwitch = findViewById<Switch>(R.id.public_switch)
        val submitBtn = findViewById<Button>(R.id.submit_btn)
        val uploadBtn = findViewById<Button>(R.id.image_upload_btn)
        val cancelBtn = findViewById<Button>(R.id.cancel_btn)
        val genderDropDown = findViewById<Spinner>(R.id.gender_dropdown)
        val dropDownAdapter = ArrayAdapter.createFromResource(this,R.array.genders,R.layout.spinner_item)
        dropDownAdapter.setDropDownViewResource(R.layout.spinner_item)
        genderDropDown.adapter = dropDownAdapter

        var imageList: ArrayList<Uri> = ArrayList()
        val imageRV = findViewById<RecyclerView>(R.id.imageRV)

        //If currently editiing a character, set the default values to the character values
        if(loadCharacter == true){
            val character = Fetcher.getCurrentCharacterVal()
            nameInput.setText(character.name)
            raceInput.setText(character.race)
            birthdayInput.setText(character.birthday)
            descriptionInput.setText(character.characterDescription)
            if (character.gender != ""){
                val pos=dropDownAdapter.getPosition(character.gender)
                genderDropDown.setSelection(pos)
            }
            imageList = character.characterImages as ArrayList<Uri>
            if (character.isPublic == 1){
                isPublicSwitch.isChecked = true
            }
        }


        val imageRVAdapter = CharacterDetailAdapter(this,imageList)
        imageRV.adapter = imageRVAdapter
        imageRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)


        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                //get uri
                val uri = result.data?.data as Uri
                //get permission to use uri throughout app
                contentResolver.takePersistableUriPermission(uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION)

                imageList.add(uri)
                imageRVAdapter.notifyDataSetChanged()

            }
        }

        cancelBtn.setOnClickListener {
            this.finish()
        }

        //When upload button is clicked, start an intent that allows user to pick an image
        //Then use resultLauncher to get the resulting uri
        uploadBtn.setOnClickListener {
            val uploadIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            uploadIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            resultLauncher.launch(uploadIntent)
        }

        //When submit button pressed, store all the inputs into a new character item
        // Only the name input has to be filled for the new character to be created
        submitBtn.setOnClickListener {
            val charName = nameInput.text.toString()
            val charBirthday = birthdayInput.text.toString()
            var charGender = genderDropDown.selectedItem.toString()
            val charRace = raceInput.text.toString()
            val charDescription = descriptionInput.text.toString()
            val charImages = imageList
            val isPublic : Int

            if (isPublicSwitch.isChecked){
                isPublic = 1
            }else{
                isPublic = 0
            }
            if(charGender == "Pick Gender"){
                charGender = ""
            }

            if (charName == ""){
                Toast.makeText(this,"Character Name Not Provided", Toast.LENGTH_LONG).show()
            }
            //Check if currently editing a character
            else if (loadCharacter == true){
                val character = Fetcher.getCurrentCharacterVal()
                character.name = charName
                character.gender = charGender
                character.birthday = charBirthday
                character.race = charRace
                character.characterImages = charImages
                character.characterDescription = charDescription
                character.isPublic = isPublic
                Fetcher.setLoadCharacterVal(false)
                Fetcher.updateAdapter()
                this.finish()
            }
            else{
                val newCharacter = HomeRecyclerViewItem.CharacterItem(
                    name = charName,
                    birthday = charBirthday,
                    gender = charGender,
                    race = charRace,
                    characterDescription = charDescription,
                    characterImages = charImages,
                    isPublic = isPublic,
                    directory = DirectoryHandler.currentDirectory)
                Fetcher.itemList.add(newCharacter)
                Fetcher.updateAdapter()
                this.finish()
            }
        }
    }


}