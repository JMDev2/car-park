package com.example.dynamicfeature


import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ekenya.rnd.baseapp.R
import com.ekenya.rnd.common.abstractions.BaseActivity
import com.example.dynamicfeature.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // change status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        supportActionBar?.hide()
    }
}