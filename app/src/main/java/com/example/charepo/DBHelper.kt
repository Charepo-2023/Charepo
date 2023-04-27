package com.example.charepo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(context: Context):SQLiteOpenHelper(context, "UserLogin",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Userlogin (username TEXT primary key, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Userlogin")
    }

    fun insertdate(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("password", password)
        val result = db.insert("UserLogin",null,cv)
        if (result != -1 .toLong()) return false;
        else return true;
    }

    fun checkUserPassword(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val query = "SELECT * FROM UserLogin WHERE username= '$username' and password= '$password'"
        val cursor = db.rawQuery(query, null)

        if(cursor.count <= 0)
        {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
}