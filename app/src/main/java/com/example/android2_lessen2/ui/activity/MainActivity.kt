package com.example.android2_lessen2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.android2_lessen2.R
import com.example.android2_lessen2.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        if (PreferenceHelper.safeBool && PreferenceHelper.registrationSafe) {
            navController.navigate(R.id.noteAppFragment)
        } else if (PreferenceHelper.safeBool) {
            navController.navigate(R.id.registrationFragment)
        } else {
            navController.navigate(R.id.onBoardFragment)
        }
    }
}