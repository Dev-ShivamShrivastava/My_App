package com.example.myapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NotesDataModel::class], version = 1, exportSchema = false)
@TypeConverters(DummyArrayConverter::class,DummyClassConverter::class)
abstract class NotesDB :RoomDatabase(){
    abstract fun getNotesDao():NotesDao
}