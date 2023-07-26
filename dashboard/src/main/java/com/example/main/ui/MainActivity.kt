package com.example.main.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

import com.ekenya.rnd.common.abstractions.BaseActivity
import com.ekenya.rnd.common.utils.SharedPreferences
import com.ekenya.rnd.common.utils.SharedPreferences.setPhoneNumber
import com.example.main.R
import com.example.main.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_ContainerView) as NavHostFragment
        navHostFragment.navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    setPhoneNumber(this, "")


    // change status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        supportActionBar?.hide()
    }

    override fun onSupportNavigateUp(): Boolean {
         return  navController.navigateUp()|| super.onSupportNavigateUp()
    }
}