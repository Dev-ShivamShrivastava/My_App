package com.example.myapp.preferences

import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.example.myapp.AppController
import javax.inject.Inject


class PreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
) {
    fun storeValue(key: String, value: String) {
        editor.putString(key, value).commit()
    }

    fun storeValue(key: String, value: Boolean) {
        editor.putBoolean(key, value).commit()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false) ?: false
    }

    fun getValue(key: String): String {
        return sharedPreferences.getString(key, "null") ?: ""
    }


}