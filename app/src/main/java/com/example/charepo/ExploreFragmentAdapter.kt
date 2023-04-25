package com.example.charepo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExploreFragmentAdapter(
    val context: Context,
    val items: List<HomeRecyclerViewItem> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val sortedList = Fetcher.sortCharacters(items)

    override fun getItemViewType(position: Int): Int {
        if (sortedList[position] is HomeRecyclerViewItem.FolderItem){
            return VIEW_TYPE_FOLDER
        }else{
            return VIEW_TYPE_CHARACTER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.explore_item, parent, false)
        return CharacterViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = sortedList[position]
        if (holder is CharacterViewHolder && item is HomeRecyclerViewItem.CharacterItem){
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return sortedList.size
    }

    //Need to add owner as part of character Item
    internal inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item:HomeRecyclerViewItem.CharacterItem){
            itemView.findViewById<TextView>(R.id.charNameView).text = item.name
            if (item.characterImages!!.isEmpty()){
                itemView.findViewById<ImageView>(R.id.charImageView).setImageResource(R.drawable.default_character_icon)
            }else{
                itemView.findViewById<ImageView>(R.id.charImageView).setImageURI(item.characterImages!![0])
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CharacterDetail::class.java)
                Fetcher.setCurrentCharacterVal(item)
                itemView.context.startActivity(intent)
            }

        }
    }
    }