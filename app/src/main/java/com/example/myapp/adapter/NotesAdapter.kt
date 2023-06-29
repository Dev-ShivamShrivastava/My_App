package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.database.NotesDataModel
import com.example.myapp.databinding.ItemLayoutNotesBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {
    var list = ArrayList<NotesDataModel>()

    private lateinit var onNotesClick: OnNotesClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NotesHolder {
        return NotesHolder(
            ItemLayoutNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            onNotesClick.onNotesClick(list[position],position)
        }

        holder.itemView.setOnLongClickListener {
            onNotesClick.notesDelete(it,list[position],position)

            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NotesHolder(val binding: ItemLayoutNotesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NotesDataModel) {
            binding.model = data
        }
    }


    fun addData(newList: ArrayList<NotesDataModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun setReference(reference: OnNotesClickListener) {
        onNotesClick = reference
    }

    interface OnNotesClickListener {
        fun onNotesClick(notesData: NotesDataModel, position: Int)
        fun notesDelete(view: View, notesData: NotesDataModel, position: Int)
    }
}