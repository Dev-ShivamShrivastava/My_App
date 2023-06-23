package com.example.myapp.views.fragment.profile

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.R

class ProfileVM:ViewModel() {

    val count by lazy { MutableLiveData(0) }

    fun onClick(view:View){
        when(view.id){
            R.id.btnCount ->{
               count.value = count.value?.plus(1)
            }
        }
    }
}