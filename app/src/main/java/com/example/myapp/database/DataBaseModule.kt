package com.example.myapp.database

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Singleton
    @Provides
    fun getDB(
        @ApplicationContext context: Context
    ): NotesDB {
        return  Room.databaseBuilder(
            context,
            NotesDB::class.java, "NotesDB"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun getDao(
        getDB:NotesDB
    ): NotesDao {
        return  getDB.getNotesDao()
    }

}