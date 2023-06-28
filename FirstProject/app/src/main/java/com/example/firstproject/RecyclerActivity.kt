package com.example.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {

    private lateinit var images:Array<Int>
    private lateinit var names:Array<String>
    private lateinit var data:ArrayList<ItemModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val recycle=findViewById<RecyclerView>(R.id.recylerView)

        recycle.layoutManager=LinearLayoutManager(this)

       images= arrayOf(
           R.drawable.ic_baseline_person_24,
           R.drawable.baseline_lock_24,
           R.drawable.ic_baseline_email_24,
           R.drawable.ic_baseline_menu_24,
           R.drawable.ic_baseline_phone_24,
           R.drawable.ic_baseline_email_24,
           R.drawable.ic_baseline_person_24,
           R.drawable.baseline_lock_24,
           R.drawable.ic_baseline_email_24,
           R.drawable.ic_baseline_menu_24,
           R.drawable.ic_baseline_email_24,
           R.drawable.ic_baseline_person_24,
           R.drawable.baseline_lock_24,
           R.drawable.ic_baseline_email_24,
           R.drawable.ic_baseline_menu_24,
       )

        names= arrayOf(
            "hello",
            "welcome",
            "to the",
            "world",
            "of ",
            "android",
            "application",
            "made by",
            "recycler View",
            "example",
            "android",
            "application",
            "made by",
            "recycler View",
            "example",
        )

        data= ArrayList<ItemModel>()
        getdata()

        recycle.adapter=MyAdapter(data)
    }

    private fun getdata() {
        for(i in images.indices)
            data.add(ItemModel(images[i],names[i]))

    }
}