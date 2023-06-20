package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ItemLayoutNameListBinding

class NameListAdapter:RecyclerView.Adapter<NameListAdapter.NameListHolder>() {

    var list = ArrayList<String>()
    init {
        list.add("Shivam")
        list.add("Shubham")
        list.add("Rohit")
        list.add("Prince")
        list.add("Satyam")
        list.add("Shivam")
        list.add("Shubham")
        list.add("Rohit")
        list.add("Prince")
        list.add("Satyam")
        list.add("Shivam")
        list.add("Shubham")
        list.add("Rohit")
        list.add("Prince")
        list.add("Satyam")
        list.add("Shivam")
        list.add("Shubham")
        list.add("Rohit")
        list.add("Prince")
        list.add("Satyam")
        list.add("Shivam")
        list.add("Shubham")
        list.add("Rohit")
        list.add("Prince")
        list.add("Satyam")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameListHolder {
        return NameListHolder(ItemLayoutNameListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: NameListHolder, position: Int) {
        holder.binding.tvName.text = list[position]
    }

    class NameListHolder(val binding:ItemLayoutNameListBinding):RecyclerView.ViewHolder(binding.root){}
}