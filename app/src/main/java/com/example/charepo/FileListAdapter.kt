package com.example.charepo

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class FileListAdapter(private val context: Context, private val fileList: Array<File>)
    : RecyclerView.Adapter<FileListAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder  {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.folder_item, parent, false)
        return FileViewHolder(view)
    }

    inner class FileViewHolder(val fView: View):RecyclerView.ViewHolder(fView) {
        val folderName: TextView = fView.findViewById(R.id.folder_name)
        val image : ImageView = fView.findViewById(R.id.folder_icon)

    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = fileList[position]
        holder.folderName.text = file.name
        if (!file.isDirectory){
            holder.image.setImageResource(R.drawable.file)
        }

        holder.itemView.setOnClickListener{
            if (file.isDirectory){
                val intent = Intent(context, FileList::class.java)
                val path = file.absolutePath
                intent.putExtra("path", path)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }

    }

//    fun folderNamePopup() {
//
//        val mView = LayoutInflater.from(MainActivity()).inflate(R.layout.folder_popup, null)
//        val folderNameInput = mView.findViewById<EditText>(R.id.name_input)
//        val cancelBtn = mView.findViewById<Button>(R.id.cancel_button)
//        val okBtn = mView.findViewById<Button>(R.id.ok_button)
//
//        val nameBuilder = AlertDialog.Builder(MainActivity())
//        nameBuilder.setTitle("Folder Name")
//        nameBuilder.setView(mView)
//        nameBuilder.create()
//        nameBuilder.show()
//    }


}