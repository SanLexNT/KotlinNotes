package com.alexey.kotlinnotes.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexey.kotlinnotes.APP
import com.alexey.kotlinnotes.R
import com.alexey.kotlinnotes.REPOSITORY
import com.alexey.kotlinnotes.databinding.FragmentListNotesBinding
import com.alexey.kotlinnotes.domain.Note
import com.alexey.kotlinnotes.domain.NoteAdapter
import com.alexey.kotlinnotes.domain.db.NoteDB
import com.alexey.kotlinnotes.domain.db.NoteRealization


class ListNotesFragment : Fragment() {

    private lateinit var binding: FragmentListNotesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListNotesBinding.inflate(layoutInflater, container, false)
        APP = activity as MainActivity
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteDao = NoteDB.getInstance(APP.applicationContext).getDao()
        REPOSITORY = NoteRealization(noteDao)

        recyclerView = binding.rvNotes
        adapter = NoteAdapter()

        recyclerView.adapter = adapter
        if(resources.getBoolean(R.bool.isLandscape)){
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        } else{
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        REPOSITORY.notes.observe(viewLifecycleOwner){
            adapter.setNotes(it.asReversed())
        }

        binding.fab.setOnClickListener{

            APP.navController.navigate(R.id.action_listNotesFragment_to_addNoteFragment)
        }

        binding.toolbarMain.setOnMenuItemClickListener {

            if(it.itemId == R.id.item_info){
                APP.navController.navigate(R.id.action_listNotesFragment_to_infoFragment)
            }
            true
        }
    }

    companion object{

        fun noteClick(note:Note){

            val bundle = Bundle()
            bundle.putSerializable("NOTE", note)

            APP.navController.navigate(R.id.action_listNotesFragment_to_editNoteFragment, bundle)
        }
    }

}