package com.example.myapp.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

class NetworkCheckReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            val noConnectivity: Boolean = intent?.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false) ?: false

            if (!noConnectivity) {
               Toast.makeText(context,"Internet Active",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context,"Internet Inactive",Toast.LENGTH_LONG).show()
            }
        }
    }

}