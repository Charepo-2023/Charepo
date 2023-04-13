package com.example.charepo

import android.view.View

class DirectoryHandler {

    companion object{
        var currentDirectory : String = "root"
        var directoryStack : MutableList<String> = ArrayList()

        fun setDirectory(newDirectory:String){
            directoryStack.add(newDirectory)
            currentDirectory = newDirectory
            directoryStack.add(newDirectory)

        }

        fun getDirectory():String{
            return currentDirectory
        }


        fun previousDirectory():String{
            if(directoryStack.isEmpty()){
                currentDirectory = "root"
                return currentDirectory
            }else {
                val lastItem = directoryStack.size - 1
                val prevDir = directoryStack[lastItem]
                directoryStack.removeAt(lastItem)
                return prevDir
            }
        }

    }

}