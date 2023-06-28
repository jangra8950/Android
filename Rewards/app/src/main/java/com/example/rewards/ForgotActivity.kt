package com.example.rewards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.firstproject.R

class ForgotActivity : AppCompatActivity(){
    private lateinit var submit:Button
    private lateinit var phone:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        phone=findViewById(R.id.ph)
        submit=findViewById(R.id.btnSub)

        submit.setOnClickListener{
            val number=phone.text.toString()
            val intent= Intent(this,OtpActivity::class.java)
            intent.putExtra("key",number)
            startActivity(intent)
        }

    }
}