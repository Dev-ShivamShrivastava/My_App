package com.example.myapp.views.fragment.home

import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.adapter.NameListAdapter
import com.example.myapp.model.UserListResponse
import com.example.myapp.network.Repository
import com.example.myapp.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private val repository: Repository):ViewModel(){

    val adapter by lazy { NameListAdapter() }

   lateinit var context : WeakReference<FragmentActivity>

    init {
        adapter.setReferenceListener(object : NameListAdapter.OnSelectedListener {
            override fun onSelected(view: View, data: UserListResponse.Data) {
                view.context.showToast(data.first_name?:"")
//                view.findNavController().navigate(R.id.profileFragment)
//                Navigation.findNavController((view.context as HomeActivity), R.id.fragmentMain).navigate(R.id.profileFragment)
            }

        })
    }

    val responseLive = MutableLiveData(UserListResponse())

    init {
        callApi()
    }

    fun callApi(){
            repository.fetchData{
                CoroutineScope(Dispatchers.Main).launch {
//                responseLive.value = it
                    if (it.data!=null){
                        adapter.setData(it.data ?: ArrayList())
                    }else{
                        Toast.makeText(context.get(),"network problem",Toast.LENGTH_LONG).show()
                    }

                }
        }
    }

}