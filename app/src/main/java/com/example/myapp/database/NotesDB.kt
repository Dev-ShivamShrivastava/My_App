package com.example.myapp.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NotesDataModel::class], version = 2 ,exportSchema = true, autoMigrations = [
    AutoMigration(1,2)])
@TypeConverters(DummyArrayConverter::class,DummyClassConverter::class)
abstract class NotesDB :RoomDatabase(){
    abstract fun getNotesDao():NotesDao
}