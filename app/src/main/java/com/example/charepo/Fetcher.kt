package com.example.charepo

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import java.net.URL

class Fetcher {
    companion object{
            var itemList = mutableListOf<HomeRecyclerViewItem>()
            var folderIcon:Uri? = null
            lateinit var recyclerAdapter: RecyclerAdapter
            var sortedList: MutableList<HomeRecyclerViewItem> = ArrayList()
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

        fun searchList(keyword:String){
            val searchList : MutableList<HomeRecyclerViewItem> = ArrayList()
            for (i in sortedList){
                if (i is HomeRecyclerViewItem.FolderItem){
                    if (i.name.toString().lowercase().contains(keyword.lowercase())){
                        searchList.add(i)
                    }
                }
                if (i is HomeRecyclerViewItem.CharacterItem){
                    if (i.name.toString().lowercase().contains(keyword.lowercase())){
                        searchList.add(i)
                    }
                }
            }
            searchList.sortWith((compareByDescending<HomeRecyclerViewItem> { it.javaClass.name.lowercase() }.thenBy{ it.toString().lowercase() }))
            recyclerAdapter.sortedList = searchList
            recyclerAdapter.notifyDataSetChanged()
        }

        fun searchExploreList(keyword: String, adapter: ExploreFragmentAdapter){
            val searchList : MutableList<HomeRecyclerViewItem> = ArrayList()
            for (i in sortedList){
                if (i is HomeRecyclerViewItem.CharacterItem && i.isPublic == 1){
                    if (i.name.toString().lowercase().contains(keyword.lowercase()) ||
                        i.owner.toString().lowercase().contains(keyword.lowercase())){
                        searchList.add(i)
                    }
                }
            }
            searchList.sortWith((compareByDescending<HomeRecyclerViewItem> { it.javaClass.name.lowercase() }.thenBy{ it.toString().lowercase() }))
            adapter.sortedList = searchList
            adapter.notifyDataSetChanged()
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

        fun setFolderIconVal(uri: Uri?){
            folderIcon = uri
        }
        fun getFolderIconVal():Uri?{
            return folderIcon
        }

        fun initializeAdapter(givenContext: Context, list:MutableList<HomeRecyclerViewItem>, homeFragment: HomeFragment): RecyclerAdapter{
                recyclerAdapter = RecyclerAdapter(givenContext,list,homeFragment)
                itemList = list
                return recyclerAdapter
            }

        fun updateAdapter(){
                recyclerAdapter.sortedList = sortList(itemList)
                recyclerAdapter.notifyDataSetChanged()
            }

        fun updateFolderName(oldName:String, newName:String, changingItem: HomeRecyclerViewItem,newIcon:Uri?){
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
            if (newIcon != null){
                changingItem.setIcon(changingItem, newIcon)
            }
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

            val url = URL("https://i.imgur.com/iy7mQPY.png")
            val charImages : ArrayList<Uri> = ArrayList()
            charImages.add(Uri.parse(url.toURI().toString()))
            itemList.add(HomeRecyclerViewItem.CharacterItem("James",
                directory = "root",
                characterDescription = "This is James. He loves to drink coffee",
                gender = "",
                birthday = "",
                race = "",
                isPublic = 1,
                characterImages = charImages,
                owner = "DavidLovesToDraw12"))

            val george : ArrayList<Uri> = ArrayList()
            george.add(Uri.parse("https://i.imgur.com/1IL2TD7.png".toUri().toString()))
            itemList.add(HomeRecyclerViewItem.CharacterItem("George",
                directory = "root",
                characterDescription = "Nice guy George.",
                gender = "",
                birthday = "",
                race = "",
                isPublic = 1,
                characterImages = george,
                owner = "DavidLovesToDraw12"))

            val sarah : ArrayList<Uri> = ArrayList()
            sarah.add(Uri.parse("https://i.imgur.com/t5bPl4i.png".toUri().toString()))

            itemList.add(HomeRecyclerViewItem.CharacterItem("Sarah",
                directory = "root",
                characterDescription = "It's Sarah!",
                gender = "",
                birthday = "",
                race = "",
                isPublic = 1,
                characterImages = sarah,
                owner = "DavidLovesToDraw12"))

            itemList.add(HomeRecyclerViewItem.FolderItem("Women", directory = "root", owner = "mikala"))
            itemList.add(HomeRecyclerViewItem.FolderItem(
                "Lightning Thief",
                directory = "root",
                owner = "mikala",
                icon = Uri.parse("https://i.imgur.com/6oE8paP.png".toUri().toString())))

            val percy : ArrayList<Uri> = ArrayList()
            percy.add(Uri.parse("https://i.imgur.com/O0wPMCS.png".toUri().toString()))
            percy.add(Uri.parse("https://i.imgur.com/1q1ejhT.png".toUri().toString()))
            percy.add(Uri.parse("https://i.imgur.com/6HXVTHb.png".toUri().toString()))
            itemList.add(HomeRecyclerViewItem.CharacterItem("Percy Jackson",
                directory = "Lightning Thief",
                characterDescription = "Percy Jackson, the protagonist of the story, is a young boy who discovers he is the son of the Greek god Poseidon. Although Percy has been defined as a troubled youth all his life, he desires to learn the truth about his identity and where he fits into the world. Several of his character traits, such as his dyslexia and ADHD, are perceived as problems by the adults around him. As a result, Percy struggles with negative self-image and a desire to prove himself. He loves his mother, who is the most important person in his life, and her disappearance prompts Percy to accept his quest.",
                isPublic = 1,
                birthday = "August 18, 1993",
                race = "Demigod",
                gender = "Male",
                characterImages = percy ,
                owner = "mikala"))

            val annabeth : ArrayList<Uri> = ArrayList()
            annabeth.add(Uri.parse("https://i.imgur.com/nMuxY7r.png".toUri().toString()))
            annabeth.add(Uri.parse("https://i.imgur.com/eWj6Zqv.png".toUri().toString()))

            itemList.add(HomeRecyclerViewItem.CharacterItem(
                "Annabeth Chase",
                directory = "Lightning Thief",
                characterDescription = "Annabeth Chase is a fictional character in Rick Riordan's Percy Jackson and the Olympians series. She is a demigod, meaning she is half-mortal and half god. Her father is the mortal Frederick Chase and her mother is Athena, the goddess of wisdom. She first appears in the first novel of the series, The Lightning Thief.",
                isPublic = 1,
                birthday = "July 12, 1993",
                race = "Demigod",
                gender = "Female",
                characterImages = annabeth ,
                owner = "mikala"))

            val grover : ArrayList<Uri> = ArrayList()
            grover.add(Uri.parse("https://i.imgur.com/TVBpFa2.png".toUri().toString()))
            itemList.add(HomeRecyclerViewItem.CharacterItem(
                "Grover Underwood",
                directory = "Lightning Thief",
                characterDescription = "A satyr and keeper in charge of protecting Percy in order to obtain his searcher license to find Pan. Initially introduced as Percy’s anxious friend from school, Grover ultimately reveals his true identity and joins Percy on his quest to save the world.",
                isPublic = 1,
                birthday = "June 5, 1978",
                race = "Satyr",
                gender = "Male",
                characterImages = grover ,
                owner = "mikala"))

            val luke : ArrayList<Uri> = ArrayList()
            luke.add(Uri.parse("https://i.imgur.com/vaotatg.png".toUri().toString()))
            itemList.add(HomeRecyclerViewItem.CharacterItem(
                "Luke Castellan",
                directory = "Lightning Thief",
                characterDescription = "A satyr and keeper in charge of protecting Percy in order to obtain his searcher license to find Pan. Initially introduced as Percy’s anxious friend from school, Grover ultimately reveals his true identity and joins Percy on his quest to save the world.",
                isPublic = 1,
                birthday = "",
                race = "Demigod",
                gender = "Male",
                characterImages = luke ,
                owner = "mikala"))

            val charlieImage : ArrayList<Uri> = ArrayList()
            charlieImage.add(Uri.parse("https://i.imgur.com/FsQLlD0.png".toUri().toString()))
            charlieImage.add(Uri.parse("https://i.imgur.com/cZvZ1Le.png".toUri().toString()))

            itemList.add(HomeRecyclerViewItem.CharacterItem("Charlie",
                directory = "root",
                characterDescription = "Charlie Bucket is the main protagonist of the 1964 Roald Dahl book Charlie and the Chocolate Factory and the 1971 and 2005 film adaptations of the book. Despite being a stereotypical boy, Charlie is a noble, caring hero and one of the best in British literature.",
                isPublic = 1,
                birthday = "April 15, 1987",
                race = "White",
                gender = "Male",
                characterImages = charlieImage,
                owner = "Jennifer"))

            itemList.add(HomeRecyclerViewItem.FolderItem("Book 6", directory = "root", owner = "mikala"))
            itemList.add(HomeRecyclerViewItem.FolderItem("Blue","Book 1"))
            itemList.add(HomeRecyclerViewItem.FolderItem("Red","Book 1"))
            itemList.add(HomeRecyclerViewItem.CharacterItem(
                "Pete",
                characterDescription = "Pete lives in the forest and tracks animals.",
                directory = "Book 1",
                isPublic = 0))
            return itemList
        }





    }
}