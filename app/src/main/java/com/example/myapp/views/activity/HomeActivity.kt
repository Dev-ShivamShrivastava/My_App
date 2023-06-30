package com.example.myapp.views.activity

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myapp.R
import com.example.myapp.broadcast.NetworkCheckReceiver
import com.example.myapp.databinding.ActivityHomeBinding
import com.example.myapp.workmanager.PeriodicWM
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var navController: NavController
    val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navController = findNavController(R.id.fragmentMain)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
        registerBroadcast()
        getFcmToken()


//        OneTimeWM.oneTimeWorkManager(this)
        PeriodicWM.periodicWorkManager(this)

    }

    private fun getFcmToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("tokenE", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            Log.d("token", token)
//            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        })
    }

    private fun registerBroadcast(){
        val broadcastReceiver: BroadcastReceiver = NetworkCheckReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(broadcastReceiver,filter)
    }
}