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
import com.bumptech.glide.Glide

class ExploreFragmentAdapter(
    val context: Context,
    val items: List<HomeRecyclerViewItem> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var sortedList = Fetcher.sortCharacters(items)

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
            itemView.findViewById<TextView>(R.id.ownerNameView).text = "Created By: " + item.owner

            if (item.characterImages!!.isEmpty()){
                itemView.findViewById<ImageView>(R.id.charImageView).setImageResource(R.drawable.default_character_icon)
            }else {
                Glide.with(itemView.context)
                    .load(item.characterImages!![0])
                    .into(itemView.findViewById(R.id.charImageView))
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CharacterDetailPublic::class.java)
                Fetcher.setCurrentCharacterVal(item)
                itemView.context.startActivity(intent)
            }

        }
    }
    }