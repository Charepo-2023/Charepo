package com.example.charepo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

const val VIEW_TYPE_FOLDER = 1
const val VIEW_TYPE_CHARACTER =2

class RecyclerAdapter(
    val context: Context,
    val items: List<HomeRecyclerViewItem>,
    val recyclerFragment: RecyclerFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val sortedList = Fetcher.sortList(items)


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
            itemView.findViewById<ImageView>(R.id.folderIcon).setImageResource(R.drawable.folder)
            itemView.setOnClickListener {
                DirectoryHandler.setDirectory(item.name.toString())
                recyclerFragment.checkBtnVisibility()
                Fetcher.updateAdapter()
            }
        }
    }

    internal inner class CharacterViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(item:HomeRecyclerViewItem.CharacterItem){
            itemView.findViewById<TextView>(R.id.characterName).text = item.name
            itemView.findViewById<ImageView>(R.id.characterImage).setImageResource(R.drawable.ic_launcher_background)
        }
    }


}