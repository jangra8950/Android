package com.example.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity2 : AppCompatActivity() {

    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Log.i("Tag","SecondActivity: OnCreate")
        btn = findViewById(R.id.btn2)
        btn.setOnClickListener {
            finish()
        }
    }

//    override fun onStart() {
//        super.onStart()
//        Log.i("Tag","SecondActivity: OnStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.i("Tag","SecondActivity: OnResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.i("Tag","SecondActivity: OnPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.i("Tag","SecondActivity: OnStop")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.i("Tag","SecondActivity: OnRestart")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.i("Tag","SecondActivity: OnDestroy")
//    }
}