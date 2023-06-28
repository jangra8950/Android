package com.example.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.firstproject.R
import com.example.firstproject.databinding.FragmentMapsBinding
import com.example.viewModel.MapsViewModel


class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding
    val args: MapsFragmentArgs by navArgs()
    private val viewModel:MapsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)

       navigation()
        // binding.txtMap.text=args.firstName


        return binding.root
    }

    fun navigation(){
        binding.mapBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mapsFragment_to_googleMapsFragment)
        }

        binding.searchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mapsFragment_to_searchLocationFragment)
        }
        viewModel.text.observe(viewLifecycleOwner) {

            binding.txtMap.text = it
        }
    }


}


