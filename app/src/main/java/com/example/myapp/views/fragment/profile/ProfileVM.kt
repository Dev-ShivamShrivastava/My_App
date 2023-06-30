package com.example.myapp.views.fragment.profile

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.R
import com.example.myapp.adapter.ContactListAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor():ViewModel() {


    val adapter by lazy { ContactListAdapter() }
    fun onClick(view:View){
        when(view.id){

        }
    }
}