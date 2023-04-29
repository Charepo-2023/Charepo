package com.example.charepo

import android.content.Context
import android.net.Uri
import java.net.URL

class Fetcher {
    companion object{
            var itemList = mutableListOf<HomeRecyclerViewItem>()
            lateinit var recyclerAdapter: RecyclerAdapter
            val sortedList: MutableList<HomeRecyclerViewItem> = ArrayList()
            var currentCharacter : HomeRecyclerViewItem.CharacterItem? = null
            var loadCharacter = false


        fun sortList(unsortedList: List<HomeRecyclerViewItem>) : List<HomeRecyclerViewItem>{
            sortedList.clear()
            for(i in unsortedList){
                if(i is HomeRecyclerViewItem.FolderItem && i.owner== LoginTracker.getCurrentUserName()){
                    if (i.directory == DirectoryHandler.getDirectory()){
                        sortedList.add(i)
                    }
                }
                if(i is HomeRecyclerViewItem.CharacterItem && i.owner== LoginTracker.getCurrentUserName()){
                    if (i.directory == DirectoryHandler.getDirectory()){
                        sortedList.add(i)
                    }
                }
            }
            sortedList.sortWith((compareByDescending<HomeRecyclerViewItem> { it.javaClass.name.lowercase() }.thenBy{ it.toString().lowercase() }))
            return sortedList
        }
        fun sortCharacters(unsortedList: List<HomeRecyclerViewItem>):List<HomeRecyclerViewItem>{
            sortedList.clear()
            for(i in unsortedList) {
                if(i is HomeRecyclerViewItem.CharacterItem){
                    if (i.isPublic == 1){
                        sortedList.add(i)
                    }
                }
            }
            sortedList.sortWith((compareByDescending<HomeRecyclerViewItem> { it.javaClass.name.lowercase() }.thenBy{ it.toString().lowercase() }))
            return sortedList
        }


        fun getRecyclerList(): MutableList<HomeRecyclerViewItem>{
                return itemList
            }

        fun initializeAdapter(givenContext: Context, list:MutableList<HomeRecyclerViewItem>, homeFragment: HomeFragment): RecyclerAdapter{
                recyclerAdapter = RecyclerAdapter(givenContext,list,homeFragment)
                itemList = list
                return recyclerAdapter
            }

        fun updateAdapter(){
                sortList(itemList)
                recyclerAdapter.notifyDataSetChanged()
            }

        fun updateFolderName(oldName:String, newName:String, changingItem: HomeRecyclerViewItem){
                for (i in itemList){
                    if(i is HomeRecyclerViewItem.CharacterItem){
                        if (i.directory == oldName){
                            i.directory = newName
                        }
                    }
                    if (i is HomeRecyclerViewItem.FolderItem){
                        if (i.directory == oldName){
                            i.directory = newName
                        }
                    }
                }
                changingItem.setName(changingItem,newName)
                updateAdapter()
            }

        fun setCurrentCharacterVal(character:HomeRecyclerViewItem.CharacterItem){
            currentCharacter = character
        }

        fun getCurrentCharacterVal(): HomeRecyclerViewItem.CharacterItem {
            return currentCharacter as HomeRecyclerViewItem.CharacterItem
        }

        fun setLoadCharacterVal(boolean: Boolean){
            loadCharacter = boolean
        }

        fun getLoadCharacterVal():Boolean{
            return loadCharacter
        }

        fun getItems(): MutableList<HomeRecyclerViewItem>{
            val charImages : ArrayList<Uri> = ArrayList()
            val url = URL("https://i.imgur.com/Oc9ACvH.png")
            charImages.add(Uri.parse(url.toURI().toString()))
            charImages.add(Uri.parse(url.toURI().toString()))
            itemList.add(HomeRecyclerViewItem.CharacterItem("James", directory = "root",
                characterDescription = "This is James",
                isPublic = 1, characterImages = charImages,
            owner = "DavidLovesToDraw12"))
            itemList.add(HomeRecyclerViewItem.FolderItem("Women", directory = "root", owner = "mikala"))
            itemList.add(HomeRecyclerViewItem.FolderItem("Book 1", directory = "root"))
            itemList.add(HomeRecyclerViewItem.CharacterItem("Charlie", directory = "root",
                characterDescription = "Charlie Bucket is the main protagonist of the 1964 Roald Dahl book Charlie and the Chocolate Factory and the 1971 and 2005 film adaptations of the book. Despite being a stereotypical boy, Charlie is a noble, caring hero and one of the best in British literature.",
                isPublic = 1,
                birthday = "April 15, 1987",
                race = "White",
                gender = "Male",
            owner = "mikala"))
            itemList.add(HomeRecyclerViewItem.FolderItem("Book 6", directory = "root", owner = "mikala"))
            itemList.add(HomeRecyclerViewItem.FolderItem("Blue","Book 1"))
            itemList.add(HomeRecyclerViewItem.FolderItem("Red","Book 1"))
            itemList.add(HomeRecyclerViewItem.CharacterItem("Pete",
                characterDescription = "Pete lives in the forest and tracks animals.",
                directory = "Book 1",
                isPublic = 0))
            return itemList
        }





    }
}