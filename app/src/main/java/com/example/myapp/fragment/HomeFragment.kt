package com.example.myapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapp.NameListAdapter
import com.example.myapp.R
import com.example.myapp.databinding.FragmentHomeBinding
import com.google.android.material.button.MaterialButton


class HomeFragment : Fragment() {

    val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    val adapter by lazy { NameListAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view  = inflater.inflate(R.layout.fragment_home, container, false)

        binding.btnProfile.setOnClickListener{
            findNavController().navigate(R.id.profileFragment)
        }
        binding.rvNameList.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}