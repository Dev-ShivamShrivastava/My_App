package com.example.myapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapp.adapter.NameListAdapter
import com.example.myapp.R
import com.example.myapp.databinding.FragmentHomeBinding
import com.example.myapp.model.UserListResponse
import com.example.myapp.network.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    val adapter by lazy { NameListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.btnProfile.setOnClickListener{
            findNavController().navigate(R.id.profileFragment)
        }
        adapter.setReferenceListener(object :NameListAdapter.OnSelectedListener{
            override fun onSelected(data: UserListResponse.Data) {
                Toast.makeText(requireContext(), data.first_name, Toast.LENGTH_SHORT).show()
            }

        })
        binding.rvNameList.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
           val response =  RetrofitHelper.getRetrofitClient().getUserList()
            CoroutineScope(Dispatchers.Main).launch {
                adapter.setData(response.body()?.data?: ArrayList())
            }
            Log.e("response-->", response.body().toString())
        }
    }

//Glide or Picasso

}