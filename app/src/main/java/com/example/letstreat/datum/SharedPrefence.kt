package com.example.letstreat.datum

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun clear() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}