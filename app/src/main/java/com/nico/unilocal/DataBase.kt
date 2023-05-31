package com.nico.unilocal

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context): SQLiteOpenHelper(context, "unidolcadb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sqlCreateTableUser = "create table User (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, lastname TEXT, email TEXT, password TEXT, city TEXT)"
        var sqlCreateTablePlace = "create table Place (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, schedule TEXT, phone TEXT, category TEXT, comments TEXT, ratings TEXT, state INTEGER, lat REAL, lng REAL, moderator INTEGER , FOREIGN KEY (moderator) REFERENCES User(id) ON DELETE SET NULL)"

        db?.execSQL(sqlCreateTableUser)
        db?.execSQL(sqlCreateTablePlace)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")

    }

    fun insertUser(user: User): String {
        val db = this.writableDatabase
        var dto = ContentValues()

        dto.put("name", user.name)
        dto.put("lastname", user.lastname)
        dto.put("email", user.email)
        dto.put("password", user.password)
        dto.put("city", user.city)

        var res = db.insert("User", null, dto)
        return if (res== (-1).toLong()){
            "Failed Request"
        }else {
            "Inserted (ok)"
        }
        db.close()
    }


    fun findCredentialUser(username: String, password: String): Boolean {
        val db = this.writableDatabase
        var res: Boolean

        val query = "SELECT * FROM User WHERE email='$username' AND password='$password' "
        val cursor = db.rawQuery(query, null)

        res = cursor.count > 0


        cursor.close()
        return res
    }

    fun insertPlace(place: Place): String {
        val db = this.writableDatabase
        var dto = ContentValues()

        dto.put("name", place.name)
        dto.put("description", place.description)
        dto.put("schedule", place.schedule)
        dto.put("category", place.category)
        dto.put("comments", place.comments)
        dto.put("ratings", place.ratings)
        dto.put("state", place.state)
        dto.put("lat", place.lat)
        dto.put("lng", place.lng)


        var res = db.insert("Place", null, dto)
        println(res)
        return if (res== (-1).toLong()){
            "Failed Request"
        }else {
            "Inserted (ok)"
        }
        db.close()
    }

    fun findAll(): Cursor? {
        val p0 = this.writableDatabase
        return p0.rawQuery("SELECT * FROM Place", null)
    }
}