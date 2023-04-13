package com.example.charepo

import android.content.Context

class Fetcher {
    companion object{
            var itemList = mutableListOf<HomeRecyclerViewItem>()
            lateinit var recyclerAdapter: RecyclerAdapter
            val sortedList: MutableList<HomeRecyclerViewItem> = ArrayList()


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
            sortedList.sortWith((compareByDescending<HomeRecyclerViewItem> { it.javaClass.name }.thenBy{ it.toString() }))
            return sortedList
        }


            fun getRecyclerList(): MutableList<HomeRecyclerViewItem>{
                return itemList
            }

            fun initializeAdapter(givenContext: Context,list:MutableList<HomeRecyclerViewItem>, recyclerFragment: RecyclerFragment): RecyclerAdapter{
                recyclerAdapter = RecyclerAdapter(givenContext,list,recyclerFragment)
                itemList = list
                return recyclerAdapter
            }

            fun updateAdapter(){
                sortList(itemList)
                recyclerAdapter.notifyDataSetChanged()
            }



            fun getEmails(): MutableList<HomeRecyclerViewItem>{
            val data : MutableList<HomeRecyclerViewItem> =ArrayList()
            data.add(HomeRecyclerViewItem.CharacterItem("James", directory = "Home"))
            data.add(HomeRecyclerViewItem.FolderItem("Women", directory = "root"))
            data.add(HomeRecyclerViewItem.FolderItem("Book 1", directory = "root"))
            data.add(HomeRecyclerViewItem.CharacterItem("Charlie", directory = "root"))
            data.add(HomeRecyclerViewItem.FolderItem("Book 6", directory = "root"))
            data.add(HomeRecyclerViewItem.FolderItem("Blue","Book 1"))
            data.add(HomeRecyclerViewItem.FolderItem("Red","Book 1"))
            data.add(HomeRecyclerViewItem.CharacterItem("Pete","Book 1"))
            return data
        }





    }
}