package com.example.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.Item
import com.example.data.MyAdapter
import com.example.firstproject.R
import com.example.firstproject.databinding.FragmentNotesBinding
import com.example.services.ApiHelperImpl
import com.example.services.MyRepo
import com.example.services.RetrofitInstance
import com.example.viewModel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private  val viewModel: NotesViewModel by viewModels()
    private lateinit var txt: Array<String>
    private lateinit var time: Array<String>
    private lateinit var data: ArrayList<Item>
    val args: NotesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)

        binding.recyler.layoutManager = LinearLayoutManager(activity)


//        val impl=ApiHelperImpl(RetrofitInstance)
//        val repo = MyRepo(impl)
//        viewModel = NotesViewModel(repo)

     //   viewModel.getdata()
        viewModel.response.observe(viewLifecycleOwner) {
            if (it.isNotEmpty())
                binding.recyler.adapter = MyAdapter(it)
        }
        return binding.root
    }
}

//NOTE: