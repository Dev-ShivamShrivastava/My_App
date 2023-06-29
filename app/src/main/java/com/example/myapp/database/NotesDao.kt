package com.example.myapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note:NotesDataModel)

    @Update
    suspend fun updateNote(note:NotesDataModel)

    @Query("SELECT * FROM NotesData")
    fun getAllNotes():Flow<List<NotesDataModel>>

    @Query("DELETE FROM NotesData")
    suspend fun clearData()

    @Query("DELETE FROM NotesData WHERE id = :noteId")
    suspend fun deleteByNoteId(noteId: Int)


}