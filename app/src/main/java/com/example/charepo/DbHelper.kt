package com.example.charepo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.Image

class DbHelper(context: Context) : SQLiteOpenHelper(context, "Charepo", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Character(USERID INTEGER PRIMARY KEY AUTOINCREMENT, CName TEXT, CDescription TEXT, CPicture BLOB, CPrivate BOOLEAN)")
        db?.execSQL("CREATE TABLE AccountInformation(USERID INTEGER PRIMARY KEY AUTOINCREMENT, UserName TEXT, Password TEXT, LoggedIn BOOLEAN)")
        //db?.execSQL("INSERT INTO Character(CName, CDescription, CPicture) VALUES('Dominic','???',NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun AddNewCharacter(CName: String, CDescription: String, CPicture:Image, CPrivate: Boolean, db: SQLiteDatabase?)
    {
        db?.execSQL("INSERT INTO Character(CName, CDescription, CPicture, CPrivate) VALUES(CName,CDescription,CPicture, CPrivate)")
    }

    fun AddNewAccount(EMAIL: String, PASSWORD: String,LoggedIn: Boolean, db: SQLiteDatabase?)
    {
        db?.execSQL("INSERT INTO AccountInformation(UserName, Password,LoggedIn) VALUES(EMail,PASSWORD,LoggedIn)")
    }
}