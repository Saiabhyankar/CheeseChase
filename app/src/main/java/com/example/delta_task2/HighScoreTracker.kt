package com.example.delta_task2

import android.content.Context
import android.content.SharedPreferences

fun writeToSharedPref(context: Context, key: String, value: String,Filename:String) {

    val sharedPref: SharedPreferences = context.getSharedPreferences(Filename, Context.MODE_PRIVATE)

    val editor: SharedPreferences.Editor = sharedPref.edit()

    editor.putString(key, value)

    editor.apply()
}
fun readFromSharedPreferences(context: Context, key: String, Filename: String): String {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Filename, Context.MODE_PRIVATE)
    return sharedPreferences.getString(key,"").toString() }