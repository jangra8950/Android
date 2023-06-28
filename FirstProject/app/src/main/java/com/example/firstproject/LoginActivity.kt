package com.example.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var btn:Button
    private lateinit var editEmail:TextInputEditText
    private lateinit var editPass:TextInputEditText
    private lateinit var editName:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editName=findViewById(R.id.edName)
        editEmail=findViewById(R.id.edEmail)
        editPass=findViewById(R.id.edPass)
        btn=findViewById(R.id.btn2)

        btn.setOnClickListener{
            val email=editEmail.text.toString()
            val pass=editPass.text.toString()
            if(isValidEmail(email) && isValidPass(pass))
            {
                val intent=Intent(this,OtpActivity::class.java)
                intent.putExtra("key",email)
                startActivity(intent)
                Toast.makeText(this,"Redirecting to OTP Page",Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Enter Valid Details",Toast.LENGTH_LONG).show()
        }


    }

    private fun isValidPass(pass: String): Boolean {
           return pass.length>7
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}