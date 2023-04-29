package com.example.charepo

import android.graphics.drawable.Icon
import android.media.Image
import android.net.Uri
import android.widget.ImageView
open class HomeRecyclerViewItem {
    data class FolderItem (
        var name: String? = null,
        var directory: String? = null,
        var owner: String? = null,
        var icon: Uri? = null
    ) : HomeRecyclerViewItem()

    data class CharacterItem (
        var name: String? = null,
        var characterImages: ArrayList<Uri>? = ArrayList(),
        var birthday : String? = null,
        var gender : String? = null,
        var race: String? = null,
        var characterDescription: String? = null,
        var owner:String? = null,
        var isPublic : Int? = 0,
        var directory: String? = null
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