package com.example.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.firstproject.R
import com.example.firstproject.databinding.FragmentSearchLocationBinding
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class SearchLocationFragment : Fragment() {

   private lateinit var binding:FragmentSearchLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_search_location, container, false)
        setdata()
        return binding.root
    }

    private fun setdata() {

        // Fetching API_KEY which we wrapped
//        val ai: ApplicationInfo? = activity?.applicationContext?.packageName?.let {
//            activity?.applicationContext?.packageManager
//                ?.getApplicationInfo(it, PackageManager.GET_META_DATA)
//        }
//        val value = ai?.metaData?.getString(R.string.map_key.toString())
//        val apiKey = value.toString()

        val apiKey="AIzaSyBWlG-J0cMQBOv8CN1RcDmePChQ7lt9t8U"
        // Initializing the Places API with key
        if (!Places.isInitialized()) {
            activity?.applicationContext?.let { Places.initialize(it, apiKey) }
        }
        val autoText=childFragmentManager.findFragmentById(R.id.autocomplete1) as AutocompleteSupportFragment?

        autoText!!.setPlaceFields(
            listOf(
                Place.Field.NAME,
                Place.Field.ADDRESS,
            )
        )

        autoText.setOnPlaceSelectedListener(object : PlaceSelectionListener {


            override fun onPlaceSelected(place: Place) {

                // Information about the place
                val name = place.name
                val address = place.address

                 val text="Address: $address"

                val action=SearchLocationFragmentDirections.actionSearchLocationFragmentToMapsFragment(text)
                findNavController().navigate(action)
            }
            override fun onError(status: Status) {
                Toast.makeText(activity,"Error occured",Toast.LENGTH_SHORT).show()
            }

        })
    }


}