package com.example.charepo

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

   val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
           showFiles(this)
    }

    fun showFiles(context: Context){
        val intent = Intent(context, FileList::class.java)
        val path = Environment.getExternalStorageDirectory().path
        intent.putExtra("path", path)
        startActivity(intent)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        
        //Make the navigation bar start on the home tab
        val navigationBar = findViewById<BottomNavigationView>(R.id.navigation_bar)
        val addFolderBtn = findViewById<ImageButton>(R.id.add_folder_btn)

        if(!Environment.isExternalStorageManager()) {
        val uri = Uri.parse("package:${BuildConfig.APPLICATION_ID}")
        val i = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri)
        startForResult.launch(i)
        }else{
            showFiles(this)
        }
        
        addFolderBtn.setOnClickListener{
            folderNamePopup(this)
            Toast.makeText(this,"Button Clicked",Toast.LENGTH_LONG).show()
        }

    }

}
fun folderNamePopup(context: Context){
   val nameBuilder = AlertDialog.Builder(context)
    with(nameBuilder){
        setTitle("Folder Name")
        setMessage("Button Clicked")
        show()
    }

    //nameBuilder.setTitle("Folder Name")

//    val mView = LayoutInflater.from(MainActivity()).inflate(R.layout.folder_popup,null)
//    val folderNameInput = mView.findViewById<EditText>(R.id.name_input)
//    val cancelBtn = mView.findViewById<Button>(R.id.cancel_button)
//    val okBtn = mView.findViewById<Button>(R.id.ok_button)


//    nameBuilder.setView(mView)
//    val popUp = nameBuilder.create()
//
//    okBtn.setOnClickListener{
//        if (folderNameInput.text.isEmpty()){
//            Toast.makeText(context, "Please enter a name!", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    cancelBtn.setOnClickListener{
//        popUp.dismiss()
//    }
    Toast.makeText(context, "Button pressed",Toast.LENGTH_LONG).show()
//    popUp.show()
}