package com.example.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [NotesEntity::class],version = 1)
abstract class NotesDb:RoomDatabase() {

    abstract fun getNotesDao():NotesDao
    companion object{
        @Volatile
        private var instance: NotesDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK) {
            instance ?:
            createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NotesDb::class.java,
                "note_db"
            ).build()
    }
}