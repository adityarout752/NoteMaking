package com.example.notesapp.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.repository.NotesRepository


class NotesViewModelFactory(
    val app:Application,
    val notesRepository: NotesRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return NotesViewModel(app,notesRepository) as T
    }
}