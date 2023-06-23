package com.example.myapp

import android.app.Application

class AppController:Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
         var instance:AppController ?= null

        fun getContext():AppController{
            if (instance==null){
                instance = AppController()
            }
           return instance as AppController
        }

    }


}