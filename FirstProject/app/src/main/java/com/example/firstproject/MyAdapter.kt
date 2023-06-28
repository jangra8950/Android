package com.example.firstproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val mList: List<ItemModel>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.card_view_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        val ItemModel=  mList[position]

        holder.imageView.setImageResource(ItemModel.image)
        holder.textView.text=ItemModel.text
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val textView: TextView = itemView.findViewById(R.id.text)
    }
}