package com.example.view


import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import androidx.navigation.fragment.findNavController
import com.example.firstproject.R
import com.example.firstproject.databinding.FragmentMapsGoogleBinding


import com.example.viewModel.MapsViewModel


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale


class GoogleMapsFragment : Fragment(), OnMapReadyCallback{


    private lateinit var binding: FragmentMapsGoogleBinding

    lateinit var mMap: GoogleMap
    var currentLocation: LatLng = LatLng(20.5, 78.9)

     private val viewModel:MapsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(
                inflater,
                com.example.firstproject.R.layout.fragment_maps_google,
                container,
                false
            )

            val supportMapFragment =
                childFragmentManager.findFragmentById(com.example.firstproject.R.id.map) as SupportMapFragment?

            supportMapFragment?.getMapAsync(this)



            binding.backMap.setOnClickListener {
                findNavController().navigate(R.id.action_googleMapsFragment_to_mapsFragment)
            }


            binding.mapConfirm.setOnClickListener{
                findNavController().navigate(R.id.action_googleMapsFragment_to_mapsFragment)
                //navigate(address)
            }
            viewModel.text.observe(viewLifecycleOwner) {

                binding.mapText.text = it
            }
        }
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {

        val india = LatLng(currentLocation.latitude, currentLocation.longitude)
        val s = googleMap.addMarker(
            MarkerOptions().position(india).draggable(true)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(india))
        googleMap.setOnMapClickListener {
            val midLatLng = it
            if (s != null) {
                currentLocation=(LatLng(it.latitude,it.longitude))
                s.position=it
                val address= sendAddress(it)
                viewModel.save(address)
            }
        }
        googleMap.setOnCameraMoveListener {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(currentLocation.latitude,currentLocation.longitude)))
        }
    }


    private fun sendAddress(latLng: LatLng):String {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())

           val  address =
                geocoder.getFromLocation(
                    latLng.latitude,
                    latLng.longitude,
                    1
                )!![0].getAddressLine(0)
            return address
    }

    private fun navigate(address: String) {
        val action =
            GoogleMapsFragmentDirections.actionGoogleMapsFragmentToMapsFragment(
                address.toString()
            )
        findNavController().navigate(action)
    }

}