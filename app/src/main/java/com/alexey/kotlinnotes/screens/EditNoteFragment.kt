package com.alexey.kotlinnotes.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.alexey.kotlinnotes.APP
import com.alexey.kotlinnotes.R
import com.alexey.kotlinnotes.REPOSITORY
import com.alexey.kotlinnotes.databinding.FragmentEditNoteBinding
import com.alexey.kotlinnotes.domain.Note


class EditNoteFragment : Fragment() {

    private lateinit var binding: FragmentEditNoteBinding
    private lateinit var note: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        note = arguments?.getSerializable("NOTE") as Note
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = note.title
        val description = note.description

        binding.titleEdit.setText(title)
        binding.descriptionEdit.setText(description)


    }

    override fun onStart() {
        super.onStart()

        binding.toolbarEdit.setOnMenuItemClickListener {

            if(it.itemId == R.id.item_done){
                note.title = binding.titleEdit.text.toString()
                note.description = binding.descriptionEdit.text.toString()

                REPOSITORY.editNote(note)

            }
            if(it.itemId == R.id.item_delete){
                REPOSITORY.removeNote(note)
            }
           findNavController().popBackStack()
            true
        }

        APP.onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true){

                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }

            })
    }


}
