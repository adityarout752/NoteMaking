package com.example.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notesEntity: NotesEntity)

    @Update
    suspend fun updateNote(notesEntity: NotesEntity)

    @Delete
    suspend fun deleteNote(notesEntity: NotesEntity)

    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteBody LIKE:query")
    fun searchNote(query: String?): LiveData<List<NotesEntity>>
}