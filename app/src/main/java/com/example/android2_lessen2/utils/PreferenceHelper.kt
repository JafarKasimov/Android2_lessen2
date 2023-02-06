package com.example.android2_lessen2.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var sharedPreference: SharedPreferences

    fun unit (context: Context) {
        sharedPreference = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    var safeBool: Boolean
        set(value) = sharedPreference.edit().putBoolean("key",value).apply()
        get() = sharedPreference.getBoolean("key", false)
}