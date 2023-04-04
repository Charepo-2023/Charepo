package com.example.charepo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, "Charepo", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Character(USERID INTEGER PRIMARY KEY AUTOINCREMENT, CName TEXT, CDescription TEXT, CPicture BLOB)")
        db?.execSQL("INSERT INTO Character(CName, CDescription, CPicture) VALUES('Dominic','???',NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}