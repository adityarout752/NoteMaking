package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNewNoteBinding


class NewNoteFragment :Fragment(R.layout.fragment_new_note){
    private var _binding:FragmentNewNoteBinding?=null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNewNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_new_note,menu)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}