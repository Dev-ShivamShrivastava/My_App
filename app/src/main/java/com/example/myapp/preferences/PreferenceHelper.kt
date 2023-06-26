package com.example.myapp.preferences

import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import com.example.myapp.AppController


class PreferenceHelper {
    companion object {

        fun getPref():PreferenceHelper{
            return PreferenceHelper()
        }

       private var sharedPreferences: SharedPreferences? =
            AppController.instance?.applicationContext?.getSharedPreferences(
                "MySharedPref",
                MODE_PRIVATE
            )
    }
   private var myEdit :SharedPreferences.Editor= sharedPreferences!!.edit()

    fun storeValue(key:String,value:String){
        myEdit.putString(key,value).commit()
    }

    fun storeValue(key:String,value:Boolean){
        myEdit.putBoolean(key,value).commit()
    }

    fun getBoolean(key:String):Boolean{
        return sharedPreferences?.getBoolean(key,false)?:false
    }

    fun getValue(key:String):String{
        return sharedPreferences?.getString(key,"null")?:""
    }


}