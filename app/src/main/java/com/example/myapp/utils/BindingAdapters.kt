package com.example.myapp.utils

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.squareup.picasso.Picasso


/** Binding Adapters */
object BindingAdapters {


    @BindingAdapter(value = ["setRecyclerAdapter"], requireAll = false)
    @JvmStatic
    fun setRecyclerAdapter(
        recyclerView: RecyclerView?,
        adapter: RecyclerView.Adapter<*>?
    ) {
        recyclerView?.adapter = adapter
    }

    @BindingAdapter(value = ["setImageUrl"], requireAll = false)
    @JvmStatic
    fun setImageUrl(
        imageView: ImageView,
        url: String?
    ) {
        try {
            Picasso.get().load(url).placeholder(R.drawable.ic_profile).into(imageView)
        }catch (e:Exception){}

    }
    @BindingAdapter(value = ["setDrawable"], requireAll = false)
    @JvmStatic
    fun setDrawable(
        imageView: ImageView,
        drawable: Int
    ) {
        imageView.setImageResource(drawable)
    }

    @BindingAdapter(value = ["setBackground"], requireAll = false)
    @JvmStatic
    fun setBackground(
        view: View,
        drawable: Int
    ) {
        view.background = ContextCompat.getDrawable(view.context, drawable)
    }



}