package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.db.NotesDb
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.viewModel.NotesViewModel
import com.example.notesapp.viewModel.NotesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        SetUpViewModel()
    }
    fun SetUpViewModel(){
        val notesRepository=NotesRepository(NotesDb(this))
        val viewModelProviderFactory =
            NotesViewModelFactory(
                application, notesRepository
            )

        notesViewModel = ViewModelProvider(
            this,viewModelProviderFactory).get(NotesViewModel::class.java)


    }
}