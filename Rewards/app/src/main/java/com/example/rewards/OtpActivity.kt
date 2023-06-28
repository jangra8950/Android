package com.example.rewards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.firstproject.DashboardActivity
import com.example.firstproject.HomeActivity
import com.example.firstproject.R

class OtpActivity : AppCompatActivity() {

    private lateinit var resend:TextView
    private lateinit var verify:Button
    private lateinit var editText:TextView
    private lateinit var otp1:EditText
    private lateinit var otp2:EditText
    private lateinit var otp3:EditText
    private lateinit var otp4:EditText
    val a:String="Sent Via SMS to "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        otp1=findViewById(R.id.otp1)
        otp2=findViewById(R.id.otp2)
        otp3=findViewById(R.id.otp3)
        otp4=findViewById(R.id.otp4)
        verify=findViewById(R.id.btn)
        editText=findViewById(R.id.edtext)

        verify.setOnClickListener{

            val otp=StringBuilder().append(otp1.text.toString()).append(otp2.text.toString())
                .append(otp3.text.toString()).append(otp4.text.toString())
            if(otp.length<4)
                Toast.makeText(this,"Enter Valid OTP",Toast.LENGTH_LONG).show()
            else{
                  val intent= Intent(this, HomeActivity::class.java)
                  startActivity(intent)
            }
        }

        resend=findViewById(R.id.resend)
        resend.setOnClickListener{
            Toast.makeText(this,"Otp Sent Again",Toast.LENGTH_SHORT).show()
        }

        val intent=intent
        val y=   intent.getStringExtra("key")
        "$a  $y".also { editText.text = it }

        otp1.addTextChangedListener(textWatcher1())

        otp2.addTextChangedListener(textWatcher2())

        otp3.addTextChangedListener(textWatcher3())

        otp4.addTextChangedListener(textWatcher4())

    }


    private fun textWatcher4() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            if(otp4.text.toString().isEmpty())
                otp3.requestFocus()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    private fun textWatcher3() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (otp3.text.toString().length == 1)
                otp4.requestFocus()
            else if(otp3.text.toString().isEmpty())
                otp2.requestFocus()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    private fun textWatcher2() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (otp2.text.toString().length == 1)
                otp3.requestFocus()
            else if(otp2.text.toString().isEmpty())
                otp1.requestFocus()
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

    private fun textWatcher1() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (otp1.text.toString().length == 1)
                otp2.requestFocus()
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }
}