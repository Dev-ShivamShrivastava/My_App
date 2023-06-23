package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.databinding.ItemLayoutNameListBinding
import com.example.myapp.model.UserListResponse
import com.squareup.picasso.Picasso

class NameListAdapter:RecyclerView.Adapter<NameListAdapter.NameListHolder>() {

    var list = ArrayList<UserListResponse.Data>()

    var listener : OnSelectedListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameListHolder {
        return NameListHolder(ItemLayoutNameListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: NameListHolder, position: Int) {
        holder.binding.tvName.text = "${list[position].first_name} ${list[position].last_name}"
        Picasso.get().load(list[position].avatar).into(holder.binding.ivUserImage)
        if (list[position].isSelected){
            holder.binding.ivSelect.setImageResource(R.drawable.ic_check)
        }else{
            holder.binding.ivSelect.setImageResource(R.drawable.ic_uncheck)
        }

        holder.binding.root.setOnClickListener {
            selectSingleItem(position)
            listener?.onSelected(list[position])
        }

    }

    fun selectSingleItem(position: Int){
        list.forEachIndexed { index, data ->
            data.isSelected = index == position
        }
        notifyDataSetChanged()
    }

    fun setData(newData:ArrayList<UserListResponse.Data>){
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    class NameListHolder(val binding:ItemLayoutNameListBinding):RecyclerView.ViewHolder(binding.root){}

    fun setReferenceListener(refernce:OnSelectedListener){
        listener = refernce
    }


    interface OnSelectedListener{
        fun onSelected(data:UserListResponse.Data)
    }
}