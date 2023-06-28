package com.example.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.firstproject.R
import com.example.firstproject.databinding.ActivityMatchBinding
import com.example.viewModel.MatchViewModel

class MatchActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMatchBinding
    private lateinit var viewModel:MatchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_match)

        viewModel= ViewModelProvider(this)[MatchViewModel::class.java]
       // viewModel= MatchViewModel()
        binding.myViewModel=viewModel

        binding.lifecycleOwner = this
       // viewModel.run { check() }
    }
}