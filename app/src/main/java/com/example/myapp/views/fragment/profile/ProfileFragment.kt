package com.example.myapp.views.fragment.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.myapp.R
import com.example.myapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    val binding by lazy { FragmentProfileBinding.inflate(layoutInflater).apply {
        model = viewModel
    } }

    val viewModel :ProfileVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.count.observe(viewLifecycleOwner){
            binding.tvCount.text = "${it}"
        }

        return binding.root
    }


}