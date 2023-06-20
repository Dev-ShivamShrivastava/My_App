package com.example.myapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myapp.R
import com.example.myapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragmentMain)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)

    }
}