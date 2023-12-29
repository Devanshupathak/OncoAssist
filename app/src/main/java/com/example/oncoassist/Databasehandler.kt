package com.example.oncoassist

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASE_NAME="OncoAssist"
val TABLE_NAME="Users"
val COL_USERNAME="username"
val COL_EMAIL="email"
val COL_PASSWORD="password"
val COL_ID="id"

class Databasehandler(var context:Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE" + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_USERNAME + " VARCHAR (256)," +
                COL_EMAIL +" VARCHAR (256))," +
                COL_PASSWORD +" VARCHAR (256))";
        db?.execSQL (createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    @SuppressLint("SuspiciousIndentation")
    fun insertData (user: SignIn) {
        val db = this.writableDatabase
        val cv = ContentValues ()
        cv.put(COL_USERNAME, user.name)
        cv.put (COL_EMAIL, user.email)
        cv.put (COL_PASSWORD, user.password)
        val result = db.insert (TABLE_NAME,null, cv)
        if (result == -1.toLong () )
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT) .show()
        else
        Toast.makeText (context, "Success", Toast.LENGTH_SHORT).show()
    }

}

