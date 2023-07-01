package com.example.myapp.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotesData")
data class NotesDataModel(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,
    var title: String? = null, var description: String? = null,
    var list: ArrayList<Dummy> ? = null,var dummy: Dummy?=null,var isSelected:Boolean=false
)


data class Dummy(var name: String? = null)
