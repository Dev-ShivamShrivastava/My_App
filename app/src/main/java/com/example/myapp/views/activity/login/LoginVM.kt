package com.example.myapp.views.activity.login

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myapp.R
import com.example.myapp.model.UserDetails
import com.example.myapp.preferences.PreferenceHelper
import com.example.myapp.views.activity.HomeActivity

class LoginVM:ViewModel() {

    var userDetails = ObservableField(UserDetails())

    fun onClick(view: View){
        when(view.id){
            R.id.btnLogin ->{
                Log.d("userDetails","${userDetails.get()?.email}")
                Log.d("userDetails","${userDetails.get()?.password}")
                PreferenceHelper.getPref().storeValue("isLogin",true)
                (view.context as LoginActivity).startActivity(Intent((view.context as LoginActivity), HomeActivity::class.java))
                (view.context as LoginActivity).finish()
            }
        }
    }
}