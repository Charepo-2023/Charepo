package com.example.charepo

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CharacterDetailAdapter(val context: Context,
                             val imageList: ArrayList<Uri>,
                              ): RecyclerView.Adapter<CharacterDetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = imageList[position]
        holder.bind(image)
    }

    inner class ViewHolder(characterView: View): RecyclerView.ViewHolder(characterView){
        val imageView = itemView.findViewById<ImageView>(R.id.character_image_holder)

        fun bind(image: Uri){
            imageView.setImageURI(image)
        }
    }

}