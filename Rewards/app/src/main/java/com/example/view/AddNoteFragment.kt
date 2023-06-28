package com.example.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.firstproject.R
import com.example.firstproject.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {

    private lateinit var binding:FragmentAddNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_note,container,false)

        binding.saveBtn.setOnClickListener{
            getdata()
//            findNavController().navigate(R.id.notesFragment)
        }
        return binding.root
    }

    private fun getdata() {
       val text=binding.addnote.text.toString()
       val action=AddNoteFragmentDirections.actionAddNoteFragmentToNotesFragment(text)
        findNavController().navigate(action)
    }


}