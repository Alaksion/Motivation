package com.example.motivation.infra

import android.content.Context

class SecurityPrefs(context : Context) {

    private val sharedprefs = context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

     fun storeString(key: String, value: String){
        sharedprefs.edit().putString(key, value).apply()
    }

    fun getString(key: String) : String{
        return sharedprefs.getString(key, "") ?: ""
    }

    fun removeString(key : String){
        sharedprefs.edit().remove(key).apply()
    }

}