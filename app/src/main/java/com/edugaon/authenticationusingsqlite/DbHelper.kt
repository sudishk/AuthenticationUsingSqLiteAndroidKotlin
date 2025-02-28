package com.edugaon.authenticationusingsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context):SQLiteOpenHelper(context, "edugaon_db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun registerUser(name:String, email:String, password:String): Long {
        val db = this.writableDatabase
        val user = ContentValues()
        user.put("name", name)
        user.put("email", email)
        user.put("password", password)
        val result = db.insert("users", null, user)

        if (result != -1L){
            val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE ).edit()
            sharedPreferences.putString("name_key", name)
            sharedPreferences.putString("email_key", email)
            sharedPreferences.putBoolean("login_status_key", true).apply()
        }


        return result
    }
    fun loginUser(email: String, password: String): Int {
        val db = this.writableDatabase
        val result = db.query("users", null, "email=? AND password=?", arrayOf(email, password),null, null, null)

        if (result.count>0){
            val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE ).edit()
            sharedPreferences.putBoolean("login_status_key", true).apply()
        }

        return result.count
    }


}