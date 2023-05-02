package com.example.charepo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    lateinit var backButton: Button
    lateinit var directoryHeader: TextView
    var uri: Uri? = null
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.home_fragment,container,false)
        val recyclerViewItem = view.findViewById<RecyclerView>(R.id.homeRV)

        //Add the temporary items to the recycler vies
        var itemList = Fetcher.itemList

        directoryHeader = view.findViewById(R.id.directoryHeader)

        val addButton = view.findViewById<Button>(R.id.add_button)
        val adapter = Fetcher.initializeAdapter(view.context,itemList,this)
        recyclerViewItem.adapter = adapter
        recyclerViewItem.layoutManager = GridLayoutManager(this.context,2)
        directoryHeader.text = "Home"
        DirectoryHandler.setDirectory("root")

        val searchInput = view.findViewById<EditText>(R.id.searchInput)


        searchInput.addTextChangedListener (object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (searchInput.text.isEmpty()){
                    Fetcher.updateAdapter()
                }else{
                    Fetcher.searchList(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })



         resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //get uri
                uri = result.data?.data as Uri

                //get permission to use uri throughout app
                requireActivity().contentResolver.takePersistableUriPermission(
                    uri!!,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

            }
        }

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
        val uploadBtn = dialogLayout.findViewById<Button>(R.id.image_upload_btn)

        uploadBtn.setOnClickListener {
            val uploadIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            uploadIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            resultLauncher.launch(uploadIntent)
        }


        with(builder) {
            setTitle("Enter new folder name: ")
            setPositiveButton("OK") { dialog, which ->
                Fetcher.itemList.add(
                    HomeRecyclerViewItem.FolderItem(
                        folderName.text.toString(),
                        DirectoryHandler.currentDirectory,
                        icon=uri,
                        owner = LoginTracker.getCurrentUserName()
                    )
                )
                Fetcher.updateAdapter()
                uri = null
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

    fun updateFolderIcon():Uri?{
        val uploadIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        uploadIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        resultLauncher.launch(uploadIntent)

        return uri
    }

}