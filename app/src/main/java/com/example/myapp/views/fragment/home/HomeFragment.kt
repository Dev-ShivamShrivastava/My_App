package com.example.myapp.views.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference


@AndroidEntryPoint
class HomeFragment : Fragment() {

    val binding by lazy { FragmentHomeBinding.inflate(layoutInflater).apply {
        model = homeVM
    } }

    private val homeVM: HomeVM by viewModels()

    override fun onStart() {
        super.onStart()
        homeVM.context = WeakReference(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

//        binding.rvNameList.adapter = adapter



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        CoroutineScope(Dispatchers.IO).launch {
//           val response =  RetrofitHelper.getRetrofitClient().getUserList()
//            CoroutineScope(Dispatchers.Main).launch {
//                adapter.setData(response.body()?.data?: ArrayList())
//            }
//            Log.e("response-->", response.body().toString())
//        }

//        homeVM.responseLive.observe(viewLifecycleOwner) {
//            if (it.data != null) {
//            }
//        }

    }

//Glide or Picasso

}