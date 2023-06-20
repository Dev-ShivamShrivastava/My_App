package com.example.myapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    var btnLogIn: MaterialButton? = null
    var tvSkip: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogIn = findViewById(R.id.btnLogin)
        tvSkip = findViewById(R.id.tvSkip)

        Log.d("Method","onCreate")


        btnLogIn?.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
            finish()
        }

        tvSkip?.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
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