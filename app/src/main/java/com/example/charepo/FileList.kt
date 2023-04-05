package com.example.charepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class FileList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        val filesAndFolders : Array<File>
        val folderRV = findViewById<RecyclerView>(R.id.FolderRV)

        val path = intent.getStringExtra("path")
        val fileRoot = File(path.toString())

        if(fileRoot.listFiles() == null){
            filesAndFolders = emptyArray()
        }else{
           filesAndFolders = fileRoot.listFiles() as Array<File>
        }

        folderRV.layoutManager = GridLayoutManager(this,2)
        folderRV.adapter = FileListAdapter(this,filesAndFolders)
    }

}