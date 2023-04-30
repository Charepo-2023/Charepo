package com.example.charepo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val VIEW_TYPE_FOLDER = 1
const val VIEW_TYPE_CHARACTER = 2

class RecyclerAdapter(
    val context: Context,
    val items: List<HomeRecyclerViewItem>,
    val homeFragment: HomeFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var uri:Uri? = null
    var sortedList = Fetcher.sortList(items)
//    val resultLauncher = (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            //get uri
//            uri = result.data?.data as Uri
//
//            //get permission to use uri throughout app
//            (context as Activity).contentResolver.takePersistableUriPermission(
//                uri!!,
//                Intent.FLAG_GRANT_READ_URI_PERMISSION
//            )
//
//        }
//    }

    override fun getItemViewType(position: Int): Int {
        if (sortedList[position] is HomeRecyclerViewItem.FolderItem){
            return VIEW_TYPE_FOLDER
        }else{
            return VIEW_TYPE_CHARACTER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_FOLDER){
            val itemView = LayoutInflater.from(context).inflate(R.layout.folder_item,parent,false)
            return FolderViewHolder(itemView)
        }else{
            val itemView = LayoutInflater.from(context).inflate(R.layout.character_item,parent,false)
            return CharacterViewHolder(itemView)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = sortedList[position]
        if(holder is FolderViewHolder && item is HomeRecyclerViewItem.FolderItem){
            holder.bind(item)
        }
        if (holder is CharacterViewHolder && item is HomeRecyclerViewItem.CharacterItem){
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return sortedList.size
    }

    internal inner class FolderViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(item:HomeRecyclerViewItem.FolderItem){
            itemView.findViewById<TextView>(R.id.folderName).text = item.name
            if (item.icon == null){
                itemView.findViewById<ImageView>(R.id.folderIcon).setImageResource(R.drawable.folder)
            }else{
                Glide.with(itemView.context)
                    .load(item.icon)
                    .into(itemView.findViewById(R.id.folderIcon))
            }


            //Change the directory when folder clicked
            itemView.setOnClickListener {
                DirectoryHandler.setDirectory(item.name.toString())
                homeFragment.checkBtnVisibility()
                Fetcher.updateAdapter()
            }

            //Show a popup menu when a folder is held down
            itemView.setOnLongClickListener {
                val popupMenu = PopupMenu(it.context, it)
                popupMenu.inflate(R.menu.long_press_menu)
                popupMenu.setOnMenuItemClickListener { menuItem: MenuItem? ->
                    when (menuItem!!.itemId) {
                        R.id.delete_option -> {
                            Fetcher.itemList.remove(item)
                            Fetcher.updateAdapter()
                        }
                        R.id.modify_option -> {
                                editFolder(itemView.context,item)
                        }
                    }
                    true
                }
                popupMenu.show()
                true
            }
        }
    }

    internal inner class CharacterViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(item:HomeRecyclerViewItem.CharacterItem){
            itemView.findViewById<TextView>(R.id.characterName).text = item.name
            if (item.characterImages!!.isEmpty()){
                itemView.findViewById<ImageView>(R.id.characterImage).setImageResource(R.drawable.default_character_icon)
            }else{
                Glide.with(itemView.context)
                    .load(item.characterImages!![0])
                    .into(itemView.findViewById(R.id.characterImage))
               // itemView.findViewById<ImageView>(R.id.characterImage).setImageURI(item.characterImages!![0])
            }


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CharacterDetail::class.java)
                Fetcher.setCurrentCharacterVal(item)
                itemView.context.startActivity(intent)
            }

            itemView.setOnLongClickListener {
                val popupMenu = PopupMenu(it.context, it)
                popupMenu.inflate(R.menu.long_press_menu)
                popupMenu.menu.removeItem(R.id.modify_option)
                popupMenu.setOnMenuItemClickListener { menuItem: MenuItem? ->
                    when (menuItem!!.itemId) {
                        R.id.delete_option -> {
                            Fetcher.itemList.remove(item)
                            Fetcher.updateAdapter()
                        }
                    }
                    true
                }
                popupMenu.show()
                true
            }
        }
    }

    //Function that edits the name of a folder
    fun editFolder(context: Context, folderItem: HomeRecyclerViewItem.FolderItem){
        val builder = AlertDialog.Builder(context)
        val dialogLayout = LayoutInflater.from(context).inflate(R.layout.folder_creation,null)
        val folderNameInput = dialogLayout.findViewById<EditText>(R.id.folderNameInput)
        val uploadBtn = dialogLayout.findViewById<Button>(R.id.image_upload_btn)
        folderNameInput.setText(folderItem.name)


        with(builder) {
            setTitle("Enter new folder name: ")
            setPositiveButton("OK") { dialog, which ->
                Fetcher.updateFolderName(folderItem.name.toString(), folderNameInput.text.toString(),folderItem,uri)
            }
            setNegativeButton("Cancel") { dialog, which ->
            }
            setView(dialogLayout)
            show()
        }
    }

}