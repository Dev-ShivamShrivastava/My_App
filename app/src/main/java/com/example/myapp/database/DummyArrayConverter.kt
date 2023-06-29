package com.example.myapp.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DummyArrayConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<Dummy?>? {
        val listType = object : TypeToken<ArrayList<Dummy?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toArrayList(list: ArrayList<Dummy?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}