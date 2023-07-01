package com.example.myapp.database

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE NotesData"+" ADD COLUMN 'isSelected' INTEGER  DEFAULT 0 NOT NULL")
        }
    }

    @Singleton
    @Provides
    fun getDB(
        @ApplicationContext context: Context
    ): NotesDB {
        return  Room.databaseBuilder(
            context,
            NotesDB::class.java, "NotesDB"
        ).addMigrations(MIGRATION_1_2)
//            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getDao(
        getDB:NotesDB
    ): NotesDao {
        return  getDB.getNotesDao()
    }

}