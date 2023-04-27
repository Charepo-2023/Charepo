package com.example.charepo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.Image
import android.widget.Toast
import java.sql.Blob

class CharDatabase(context: Context):SQLiteOpenHelper(context, "CharInfo",null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE charinfo (ID INTEGER primary key, information TEXT, image Image, isPublic INTEGER, dir TEXT, author TEXT, name TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS charinfor")
    }

    fun findDataInDirectory(ID: Int, information: String, image: Image, isPublic: Int, dir: String): Boolean {
        val db = this.writableDatabase
        val query = "SELECT * FROM CharInfo WHERE dir= '$dir'"
        val cursor = db.rawQuery(query, null)

        if(cursor.count <= 0)
        {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun insertdate(ID: Int, information: String, image: Image, isPublic: Int, dir: String, author: String, name: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ID", ID)
        cv.put("information", information)
        //cv.put("image", image)
        cv.put("isPublic", isPublic)
        cv.put("dir", dir)
        cv.put("author", author)
        cv.put("name", name)
        val result = db.insert("UserLogin",null,cv)
        if (result != -1 .toLong()) return false;
        else return true;
    }

    fun loopThroughItems(dir: String)
    {
        val db = this.writableDatabase
        val query = "SELECT * FROM CharInfo WHERE dir= '$dir'"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        if(cursor != null && cursor.count > 0)
        {
            cursor.moveToFirst()
            do {
                //Fetcher.itemList.add(cursor as HomeRecyclerViewItem)
            }while(cursor.moveToNext())
        }

    }
}

