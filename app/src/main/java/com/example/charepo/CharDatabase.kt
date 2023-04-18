package com.example.charepo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.Image
import java.sql.Blob

class CharDatabase(context: Context):SQLiteOpenHelper(context, "CharInfo",null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE charinfo (ID INTEGER primary key, information TEXT, image BLOB, isPublic INTEGER, dir TEXT)")
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
}

