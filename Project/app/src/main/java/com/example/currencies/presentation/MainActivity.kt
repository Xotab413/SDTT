package com.example.currencies.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.currencies.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onResume() {
        super.onResume()
        setupActionBarWithNavController(findNavController(R.id.my_nav_host_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp()
    }
}
