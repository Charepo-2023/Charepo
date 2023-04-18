package com.example.charepo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    lateinit var backButton: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.home_fragment,container,false)
        val recyclerViewItem = view.findViewById<RecyclerView>(R.id.homeRV)

        //Add the temporary items to the recycler vies
        var itemList = Fetcher.getEmails()

        val addFolderButton = view.findViewById<ImageButton>(R.id.add_folder_btn)
        val adapter = Fetcher.initializeAdapter(view.context,itemList,this)
        recyclerViewItem.adapter = adapter
        recyclerViewItem.layoutManager = GridLayoutManager(this.context,2)


        addFolderButton.setOnClickListener {
            createNewFolder(view.context)
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
    fun checkBtnVisibility(){
        if ( DirectoryHandler.currentDirectory == "root"){
            backButton.visibility = View.INVISIBLE
        }else{
            backButton.visibility = View.VISIBLE
        }
    }
}