package com.example.charepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerFragment : Fragment() {
    lateinit var finalButton: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.home_fragment,container,false)
        val recyclerViewItem = view.findViewById<RecyclerView>(R.id.homeRV)
        var itemList = Fetcher.getEmails()

        var adapter = Fetcher.initializeAdapter(view.context,itemList,this)
        recyclerViewItem.adapter = adapter
        recyclerViewItem.layoutManager = GridLayoutManager(this.context,2)

        finalButton = view.findViewById<Button>(R.id.back_btn)
        finalButton.setOnClickListener {
            DirectoryHandler.currentDirectory = DirectoryHandler.previousDirectory()
            checkBtnVisibility()
            Fetcher.updateAdapter()
        }
        return view
    }


    fun checkBtnVisibility(){
        if ( DirectoryHandler.currentDirectory == "root"){
            finalButton.visibility = View.INVISIBLE
        }else{
            finalButton.visibility = View.VISIBLE
        }
    }
}