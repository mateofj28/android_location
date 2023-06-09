package com.nico.unilocal

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class DataBase(context: Context): SQLiteOpenHelper(context, "unidolcadb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sqlCreateTableUser = "create table User (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, lastname TEXT, email TEXT, password TEXT, city TEXT)"
        var sqlCreateTableModerator = "create table Moderator (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, nickname TEXT, password TEXT)"
        var sqlCreateTablePlace = "create table Place (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, schedule TEXT, phone TEXT, category TEXT, comments TEXT, ratings TEXT, state INTEGER, lat REAL, lng REAL, createdBy INTEGER, updatedBy INTEGER , FOREIGN KEY (createdBy) REFERENCES User(id) ON DELETE SET NULL, FOREIGN KEY (updatedBy) REFERENCES Moderator(id) ON DELETE SET NULL)"
        var sqlCreateFirstModerator = "INSERT INTO Moderator (nickname, password) VALUES ('admin', 'secret123')"

        db?.execSQL(sqlCreateTableUser)
        db?.execSQL(sqlCreateTableModerator)
        db?.execSQL(sqlCreateFirstModerator)
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

    @SuppressLint("Range")
    fun getUserId(username: String): Int? {
        val db = this.readableDatabase
        val query = "SELECT id FROM User WHERE email = ?"
        val cursor = db.rawQuery(query, arrayOf(username))

        var userId: Int? = null
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex("id"))
        }

        cursor.close()
        db.close()
        return userId
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
        val p0 = this.readableDatabase
        return p0.rawQuery("SELECT * FROM Place", null)
    }

    fun updateState(name: String, state: Boolean, updatedBy: Int ): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("state", state)
        contentValues.put("updatedBy", updatedBy)

        return try {
            val rowsAffected = db.update("Place", contentValues, "name = ?", arrayOf(name))
            db.close()
            rowsAffected
        } catch (e: Exception){
            println("Error a update")
            db.close()
            -1
        }

    }
}