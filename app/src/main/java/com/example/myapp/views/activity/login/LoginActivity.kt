package com.example.myapp.views.activity.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.databinding.ActivityLoginBinding
import com.example.myapp.preferences.PreferenceHelper
import com.example.myapp.views.activity.HomeActivity
import com.example.myapp.views.activity.Signup

class LoginActivity : AppCompatActivity() {

    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater).apply {
        model = viewModel
    } }
    val viewModel : LoginVM by viewModels()

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