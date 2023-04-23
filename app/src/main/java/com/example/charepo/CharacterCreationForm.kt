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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CharacterCreationForm :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_creation)

        val submitBtn = findViewById<Button>(R.id.submit_btn)
        val uploadBtn = findViewById<Button>(R.id.image_upload_btn)
//        val uploadHolder = findViewById<LinearLayout>(R.id.image_upload_holder)
        val genderDropDown = findViewById<Spinner>(R.id.gender_dropdown)
        val dropDownAdapter = ArrayAdapter.createFromResource(this,R.array.genders,R.layout.spinner_item)
        dropDownAdapter.setDropDownViewResource(R.layout.spinner_item)
        genderDropDown.adapter = dropDownAdapter


        val imageList: ArrayList<Uri> = ArrayList()
        val imageRV = findViewById<RecyclerView>(R.id.imageRV)
        val imageRVAdapter = CharacterDetailAdapter(this,imageList)
        imageRV.adapter = imageRVAdapter
        imageRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        val nameInput = findViewById<EditText>(R.id.name_input)
        val raceInput = findViewById<EditText>(R.id.race_input)
        val birthdayInput = findViewById<EditText>(R.id.birthday_input)
        val descriptionInput = findViewById<EditText>(R.id.description_input)
        val isPublicSwitch = findViewById<Switch>(R.id.public_switch)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                //get uri
                val uri = result.data?.data as Uri
                //get permission to use uri throughout app
                contentResolver.takePersistableUriPermission(uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION)

//                val imageView = ImageView(this)
//                imageView.setImageURI(uri)
//                imageView.adjustViewBounds = true
//                imageView.setPadding(0,0,5,0)
//                uploadHolder.addView(imageView)

                imageList.add(uri)
                imageRVAdapter.notifyDataSetChanged()

            }
        }

        uploadBtn.setOnClickListener {
            val uploadIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            uploadIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            resultLauncher.launch(uploadIntent)
        }

        submitBtn.setOnClickListener {
            val charName = nameInput.text.toString()
            val charBirthday = birthdayInput.text.toString()
            val charGender = genderDropDown.selectedItem.toString()
            val charRace = raceInput.text.toString()
            val charDescription = descriptionInput.text.toString()
            val charImages = imageList
            val isPublic : Int

            if (isPublicSwitch.isChecked){
                isPublic = 1
            }else{
                isPublic = 0
            }

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