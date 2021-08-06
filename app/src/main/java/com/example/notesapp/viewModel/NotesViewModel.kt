package com.example.notesapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.db.NotesEntity
import com.example.notesapp.repository.NotesRepository
import kotlinx.coroutines.launch


class NotesViewModel(app: Application, val notesRepository: NotesRepository):AndroidViewModel(app) {


    fun addNote(notesEntity: NotesEntity) =
        viewModelScope.launch {
            notesRepository.insertNote(notesEntity)
        }

    fun deleteNote(notesEntity: NotesEntity) =
        viewModelScope.launch {
            notesRepository.deleteNote(notesEntity)
        }

    fun updateNote(notesEntity: NotesEntity) =
        viewModelScope.launch {
            notesRepository.updateNote(notesEntity)
        }

    fun getAllNote() = notesRepository.getAllNote()

    fun searchNote(query: String?) =
        notesRepository.searchNote(query)

}