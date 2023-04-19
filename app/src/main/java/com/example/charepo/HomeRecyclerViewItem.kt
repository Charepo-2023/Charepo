package com.example.charepo

import android.graphics.drawable.Icon
import android.media.Image
import android.widget.ImageView
open class HomeRecyclerViewItem {
    data class FolderItem (
        var name: String? = null,
        var directory: String? = null,
        var icon: Icon? = null
    ) : HomeRecyclerViewItem()


    data class CharacterItem (
       var name: String? = null,
       var directory: String? = null,
       var characterImage: Image? = null,
       var isPublic : Int? = 0,
       var characterDescription: String? = null

    ) : HomeRecyclerViewItem()

    fun setName(item: HomeRecyclerViewItem,newName:String){
        if (item is FolderItem){
            item.name = newName
        }
        if (item is CharacterItem){
            item.name = newName
        }
    }


}