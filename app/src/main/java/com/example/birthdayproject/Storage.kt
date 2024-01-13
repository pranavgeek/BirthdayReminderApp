package com.example.birthdayproject

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Storage {
    fun saveListToSharedPreferences(context: Context, list: List<Friend>) {
        val gson = Gson()
        val json = gson.toJson(list)

        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("MyStorage", json)
        editor.apply()
    }

    // Function to retrieve a list of objects from SharedPreferences
    fun getListFromSharedPreferences(context: Context): List<Friend> {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("MyStorage", null)

        return if (json != null) {
            val gson = Gson()
            val type = object : TypeToken<List<Friend>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}