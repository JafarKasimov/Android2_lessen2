package com.example.android2_lessen2

import android.app.Application
import com.example.android2_lessen2.utils.PreferenceHelper

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initPref()
    }

    private fun initPref(){
        PreferenceHelper.unit(this)
    }

}