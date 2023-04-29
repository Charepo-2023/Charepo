package com.example.charepo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DirectoryDatabase(context: Context): SQLiteOpenHelper(context, "Directory Info",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE dirInfo (ID INTEGER primary key, dirName TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS dirInfo")
    }

    fun insertNewDirectory(name: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("dirName", name)
        val result = db.insert("Directory Info",null,cv)
        if(result ==-1 .toLong())
        {
            return false
        }
        return true
    }
}