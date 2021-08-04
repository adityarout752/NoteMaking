package com.example.notesapp.repository

import com.example.notesapp.db.NotesDb
import com.example.notesapp.db.NotesEntity


class NotesRepository(val db:NotesDb) {


    suspend fun insertNote(notesEntity: NotesEntity)=db.getNotesDao().insertNote(notesEntity)
    suspend fun deleteNote(notesEntity: NotesEntity)=db.getNotesDao().deleteNote(notesEntity)
    suspend fun updateNote(notesEntity: NotesEntity)=db.getNotesDao().updateNote(notesEntity)

    fun getAllNote()=db.getNotesDao().getAllNotes()
    fun searchNote(query: String?) = db.getNotesDao().searchNote(query)
}