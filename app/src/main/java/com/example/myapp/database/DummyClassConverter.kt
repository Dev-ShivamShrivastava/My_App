package com.example.myapp.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class DummyClassConverter {

    @TypeConverter
    fun fromSource(source: Dummy?) :String{
        return Gson().toJson(source)
    }
    @TypeConverter
    fun  toSource(src: String): Dummy? {
        return Gson().fromJson(src,Dummy::class.java)
    }
}