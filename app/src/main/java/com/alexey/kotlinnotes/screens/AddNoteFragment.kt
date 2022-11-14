package com.alexey.kotlinnotes.screens

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.alexey.kotlinnotes.APP
import com.alexey.kotlinnotes.R
import com.alexey.kotlinnotes.REPOSITORY
import com.alexey.kotlinnotes.databinding.FragmentAddNoteBinding
import com.alexey.kotlinnotes.domain.Note
import java.security.Key


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.toolbarAdd.setOnMenuItemClickListener {

            if(it.itemId == R.id.item_done){
                val title:String = binding.title.text.toString()
                val description: String = binding.description.text.toString()

                if(isFilled(title)){
                    val note = Note(title = title, description = description)
                    REPOSITORY.insertNote(note)

                    APP.navController.navigate(R.id.action_addNoteFragment_to_listNotesFragment)
                } else{
                    binding.title.error = getString(R.string.error_title)
                }
            }
            true
        }
        APP.onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    APP.navController.navigate(R.id.action_addNoteFragment_to_listNotesFragment)

                }
            }
        )
    }

    private fun isFilled(title:String): Boolean = title.isNotEmpty()




}