package com.alexey.kotlinnotes.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexey.kotlinnotes.APP
import com.alexey.kotlinnotes.R
import com.alexey.kotlinnotes.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarInfo.setOnMenuItemClickListener {
            if(it.itemId == R.id.item_back){
                APP.navController.navigate(R.id.action_infoFragment_to_listNotesFragment)
            }
            true
        }
    }

}