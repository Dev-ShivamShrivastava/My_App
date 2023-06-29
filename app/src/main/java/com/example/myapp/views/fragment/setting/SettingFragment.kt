package com.example.myapp.views.fragment.setting

import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        return binding.root
    }

}