package com.example.charepo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    lateinit var backButton: Button
    lateinit var directoryHeader: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.home_fragment,container,false)
        val recyclerViewItem = view.findViewById<RecyclerView>(R.id.homeRV)

        //Add the temporary items to the recycler vies
        var itemList = Fetcher.getEmails()

        directoryHeader = view.findViewById(R.id.directoryHeader)

        val addButton = view.findViewById<Button>(R.id.add_button)
        val adapter = Fetcher.initializeAdapter(view.context,itemList,this)
        recyclerViewItem.adapter = adapter
        recyclerViewItem.layoutManager = GridLayoutManager(this.context,2)
        directoryHeader.text = "Home"


        //When add button clicked, dropdown menu shows and can pick either new folder or character
        addButton.setOnClickListener {
            val popupMenu = PopupMenu(it.context, it)
            popupMenu.inflate(R.menu.add_item_menu)
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem? ->
                when (menuItem!!.itemId) {
                    R.id.add_character_option -> {
                        val intent = Intent(view.context, CharacterCreationForm::class.java)
                        view.context.startActivity(intent)

                    }
                    R.id.add_folder_option -> {
                        createNewFolder(view.context)

                    }
                }
                true
            }
            popupMenu.show()
            true
        }

        //Code for the back button, needs to be double clicked at the moment
        backButton = view.findViewById(R.id.back_btn)
        backButton.setOnClickListener {
            DirectoryHandler.currentDirectory = DirectoryHandler.previousDirectory()
            checkBtnVisibility()
            Fetcher.updateAdapter()

        }
        return view
    }


    //Show a pop up with an edit text to create a new folder
    fun createNewFolder(context: Context){
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.folder_creation,null)
        val folderName = dialogLayout.findViewById<EditText>(R.id.folderNameInput)

        with(builder) {
            setTitle("Enter new folder name: ")
            setPositiveButton("OK") { dialog, which ->
                Fetcher.itemList.add(
                    HomeRecyclerViewItem.FolderItem(
                        folderName.text.toString(),
                        DirectoryHandler.currentDirectory,
                        null
                    )
                )
                Fetcher.updateAdapter()
            }
            setNegativeButton("Cancel") { dialog, which ->
            }
            setView(dialogLayout)
            show()
        }
    }

    //Checks if the directory is the root folder and hides the button when at the root directory
    //Also changes the text that shows which directory you are in
    fun checkBtnVisibility(){
        if ( DirectoryHandler.currentDirectory == "root"){
            backButton.visibility = View.INVISIBLE
            directoryHeader.text = "Home"
        }else{
            backButton.visibility = View.VISIBLE
            directoryHeader.text = DirectoryHandler.currentDirectory
        }
    }
}