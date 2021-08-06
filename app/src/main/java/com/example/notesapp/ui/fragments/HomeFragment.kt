package com.example.notesapp.ui.fragments

import NoteAdapter
import android.app.Activity
import android.os.Bundle
import android.view.*
//import android.widget.SearchView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.db.NotesEntity
import com.example.notesapp.viewModel.NotesViewModel


class HomeFragment :Fragment(R.layout.fragment_home),
SearchView.OnQueryTextListener{
private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    lateinit var notesadapter:NoteAdapter
    lateinit var notesViewModel:NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel=(activity as MainActivity).notesViewModel
        SetUpRecyclerView()
        binding.fabAddNote.setOnClickListener {mView->
            mView.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)

        }
    }



    private fun SetUpRecyclerView(){
        notesadapter=NoteAdapter()
        binding.recyclerView.apply{
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = notesadapter
        }
        activity?.let{
            notesViewModel.getAllNote().observe( viewLifecycleOwner,{
                note->
                notesadapter.differ.submitList(note)
                UpdateUI(note)
            })
        }

    }
    private fun UpdateUI(note: List<NotesEntity>) {
        if (note.isNotEmpty()) {
            binding.cardView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        } else {
            binding.cardView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)
        val mMenuSearch=menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled=false
        mMenuSearch.setOnQueryTextListener(this)

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
 /*  query?.let{query->
  searchNote(query)
   }

  */
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)
        }
        return true
    }
    private fun searchNote(query: String?) {
        val searchQuery = "%$query%"
        notesViewModel.searchNote(searchQuery).observe(
            this, { list ->
                notesadapter.differ.submitList(list)
            }
        )
    }
}