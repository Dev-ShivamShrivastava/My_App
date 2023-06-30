package com.example.myapp.views.fragment.setting

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.IntentFilter
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myapp.database.NotesDB
import com.example.myapp.database.NotesDataModel
import com.example.myapp.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : Fragment() {
     val binding by lazy { FragmentSettingBinding.inflate(layoutInflater).apply {
         model = viewModel
     } }
    val viewModel :SettingVM by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getBroadCastMessage()
        return binding.root
    }

    private fun getBroadCastMessage(){
        val listener = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                val title = p1?.getStringExtra("title")
                val desc = p1?.getStringExtra("desc")
                viewModel.notesData.set(NotesDataModel(title = title, description = desc))

            }
        }
        // In Local broadcast we can pass custom IntentFilter and make sure same IntentFilter pass from sendBroadcast
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(listener, IntentFilter("SHIVAM"));
    }

}