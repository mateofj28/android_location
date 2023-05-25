package com.nico.unilocal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class database(context: Context): SQLiteOpenHelper(context, "unidolcadb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sqlCreateTableUser = "create table User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(225), lastname VARCHAR(225), email VARCHAR(225), password VARCHAR(225), city VARCHAR(225))"
        db?.execSQL(
            sqlCreateTableUser
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertUser(user: User): String {
        val db = this.writableDatabase
        var DTO = ContentValues()

        DTO.put("name", user.name)
        DTO.put("lastname", user.lastname)
        DTO.put("email", user.email)
        DTO.put("password", user.password)
        DTO.put("city", user.city)

        var res = db.insert("User", null, DTO)
        return if (res== (-1).toLong()){
            "failed insertion in data base"
        }else {
            "Inserted (ok)"
        }
    }
}