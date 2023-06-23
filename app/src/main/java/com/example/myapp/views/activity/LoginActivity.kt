package com.example.myapp.views.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.databinding.ActivityLoginBinding
import com.example.myapp.preferences.PreferenceHelper
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d("Method","onCreate")
        Log.d("data",PreferenceHelper.getPref().getValue("data"))

        if (PreferenceHelper.getPref().getBoolean("isLogin")){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
            finish()
        }

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            PreferenceHelper.getPref().storeValue("isLogin",true)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Method","onStart")
    }

    override fun onResume() {
        super.onResume()
            Log.d("Method","onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("Method","onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d("Method","onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Method","onDestroy")

    }
}