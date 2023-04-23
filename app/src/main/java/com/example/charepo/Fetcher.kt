package com.example.charepo

import android.content.Context

class Fetcher {
    companion object{
            var itemList = mutableListOf<HomeRecyclerViewItem>()
            lateinit var recyclerAdapter: RecyclerAdapter
            val sortedList: MutableList<HomeRecyclerViewItem> = ArrayList()
            var currentCharacter : HomeRecyclerViewItem.CharacterItem? = null


        fun sortList(unsortedList: List<HomeRecyclerViewItem>) : List<HomeRecyclerViewItem>{
            sortedList.clear()
            for(i in unsortedList){
                if(i is HomeRecyclerViewItem.FolderItem ){
                    if (i.directory == DirectoryHandler.getDirectory()){
                        sortedList.add(i)
                    }
                }
                if(i is HomeRecyclerViewItem.CharacterItem){
                    if (i.directory == DirectoryHandler.getDirectory()){
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



            fun getEmails(): MutableList<HomeRecyclerViewItem>{
            val data : MutableList<HomeRecyclerViewItem> =ArrayList()
            data.add(HomeRecyclerViewItem.CharacterItem("James", directory = "Home", characterDescription = "This is James"))
            data.add(HomeRecyclerViewItem.FolderItem("Women", directory = "root"))
            data.add(HomeRecyclerViewItem.FolderItem("Book 1", directory = "root"))
            data.add(HomeRecyclerViewItem.CharacterItem("Charlie", directory = "root", characterDescription = "Charlie Bucket is the main protagonist of the 1964 Roald Dahl book Charlie and the Chocolate Factory and the 1971 and 2005 film adaptations of the book. Despite being a stereotypical boy, Charlie is a noble, caring hero and one of the best in British literature.",
                isPublic = 1,
                birthday = "April 15, 1987",
                race = "White",
                gender = "Male"))
            data.add(HomeRecyclerViewItem.FolderItem("Book 6", directory = "root"))
            data.add(HomeRecyclerViewItem.FolderItem("Blue","Book 1"))
            data.add(HomeRecyclerViewItem.FolderItem("Red","Book 1"))
            data.add(HomeRecyclerViewItem.CharacterItem("Pete", characterDescription = "Pete lives in the forest and tracks animals.", directory = "Book 1", isPublic = 0))
            return data
        }





    }
}