package com.example.vodovoz_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vodovoz_task.glide_module.GlideApp

class CustomRecyclerAdapter(private val img: List<String>, private val price: List<String>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgView: ImageView = itemView.findViewById(R.id.imgView)
        val textView: TextView = itemView.findViewById(R.id.textPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        GlideApp.with(holder.imgView).load(img[position]).into(holder.imgView);
        holder.textView.text = price[position] + "Р"
    }

    override fun getItemCount() = img.size

}