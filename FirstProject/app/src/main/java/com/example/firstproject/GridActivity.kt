package com.example.firstproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast

class GridActivity : AppCompatActivity() {

    private lateinit var grid: GridView
    private lateinit var data:ArrayList<ItemModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        grid=findViewById(R.id.gridView)
        data=ArrayList<ItemModel>()

        for(i in 1..8)
        data.add(ItemModel(R.drawable.ic_app_logo,"Hello World"))

        for(i in 1..8)
            data.add(ItemModel(R.drawable.ic_baseline_email_24,"Android"))

        for(i in 1..8)
            data.add(ItemModel(R.drawable.ic_baseline_person_24,"Application"))

        val mAdapter=GridAdapter(data,this)

        grid.adapter=mAdapter

        grid.onItemClickListener=AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent= Intent(this,RecyclerActivity::class.java)
            Toast.makeText(applicationContext,data[position].text,Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

    }
}