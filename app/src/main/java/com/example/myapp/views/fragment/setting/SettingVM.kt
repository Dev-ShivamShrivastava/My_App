package com.example.myapp.views.fragment.setting

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myapp.R
import com.example.myapp.adapter.NotesAdapter
import com.example.myapp.database.Dummy
import com.example.myapp.database.NotesDao
import com.example.myapp.database.NotesDataModel
import com.example.myapp.utils.alertBox
import com.example.myapp.utils.showToast
import com.example.myapp.utils.toArrayList
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingVM @Inject constructor(
    @ApplicationContext context: Context,
    private val databaseDao: NotesDao
) : ViewModel() {

    var notesData = ObservableField(NotesDataModel())
    var btnName = ObservableField("Add")
    val adapter by lazy { NotesAdapter() }

    init {
        adapter.setReference(object : NotesAdapter.OnNotesClickListener {

            override fun onNotesClick(notesData: NotesDataModel, position: Int) {
                this@SettingVM.notesData.set(notesData)
                btnName.set("Update")
            }

            override fun notesDelete(view: View, notesData: NotesDataModel, position: Int) {
                view.context.alertBox(message = "Are you sure, You want to delete the Note") {
                    if (it) {
                        CoroutineScope(Dispatchers.IO).launch {
                            databaseDao.deleteByNoteId(notesData.id)
                        }
                    } else {

                    }
                }
            }

        })
    }


    fun onClick(view: View) {
        when (view.id) {
            R.id.btnAdd -> {
                if (notesData.get()?.title?.trim().isNullOrEmpty()) {
                    view.context.showToast("Please enter title")
                    return
                }
                if (notesData.get()?.description?.trim().isNullOrEmpty()) {
                    view.context.showToast("Please enter description")
                    return
                }
                val list = ArrayList<Dummy>()
                list.add(Dummy("shivam"))
                list.add(Dummy("satyam"))
                list.add(Dummy("Rohit"))

                CoroutineScope(Dispatchers.Main).launch {
                    val await = CoroutineScope(Dispatchers.IO).async {
                        if (btnName.get() == "Update") {
                            val data =notesData.get()
                            data?.list = list

                            notesData.set(data)
                            databaseDao.updateNote(notesData.get() ?: NotesDataModel())
                            btnName.set("Add")
                        } else {
                            var data =notesData.get()
                            data?.list = list
                            data?.dummy = Dummy("Shiv")
                            notesData.set(data)
                            databaseDao.addNote(notesData.get() ?: NotesDataModel())
                        }
                    }
                    await.await()
                    notesData.set(NotesDataModel())
                }
            }

            R.id.btnDeleteAllData -> {
                CoroutineScope(Dispatchers.IO).launch {
                    databaseDao.clearData()
                }
            }
        }

    }

    init {
        getNoteList()
    }




    private fun getNoteList() {
        CoroutineScope(Dispatchers.IO).launch {
            databaseDao.getAllNotes().catch {
                Log.d("RoomError", "${it.message}")
            }.collect {
                Log.d("Roomdata", "$it")
                withContext(Dispatchers.Main) {
                    adapter.addData(it.toArrayList())
                }
            }
        }
    }




}