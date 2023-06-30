package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ItemContactsListBinding
import com.example.myapp.model.Contact

class ContactListAdapter:RecyclerView.Adapter<ContactListAdapter.ContactListHolder>() {

    var list = ArrayList<Contact>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListHolder {
        return ContactListHolder(ItemContactsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ContactListHolder, position: Int) {

        holder.bind(list[position])


    }

    fun setData(newData:ArrayList<Contact>){
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    class ContactListHolder( val binding:ItemContactsListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:Contact){
            binding.model = data
        }
    }




}