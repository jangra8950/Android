package com.example.firstproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.getSystemService

class GridAdapter( private val courseList: List<ItemModel>, private val context: Context):
    BaseAdapter() {


    private var layoutInflater: LayoutInflater?=null
    private lateinit var text:TextView
    private lateinit var image:ImageView

    override fun getCount(): Int {
        return courseList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView=convertView
        if(layoutInflater==null)
            layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(convertView==null)
            convertView=layoutInflater!!.inflate(R.layout.grid_item,null)


        text=convertView!!.findViewById(R.id.textGrid)
        image= convertView!!.findViewById(R.id.imageGrid)

        image.setImageResource(courseList[position].image)
        text.text = courseList[position].text

     return convertView
    }
}