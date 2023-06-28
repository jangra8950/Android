package com.example.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.chaos.view.PinView
import java.util.Random

class OtpActivity : AppCompatActivity() {

    private lateinit var image:ImageView
    private lateinit var editText:TextView
   // private lateinit var pinView: PinView
    private lateinit var resend:TextView
    private lateinit var button:Button
    val x:String = "to"
    private lateinit var otp1:EditText
    private lateinit var otp2:EditText
    private lateinit var otp3:EditText
    private lateinit var otp4:EditText
    private lateinit var otp5:EditText
    private lateinit var otp6:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        //  pinView=findViewById(R.id.pinview)
        image=findViewById(R.id.backView)
        editText=findViewById(R.id.edtext)

        resend=findViewById(R.id.etResend)
        button=findViewById(R.id.btn)

        otp1=findViewById(R.id.otp1)
        otp2=findViewById(R.id.otp2)
        otp3=findViewById(R.id.otp3)
        otp4=findViewById(R.id.otp4)
        otp5=findViewById(R.id.otp5)
        otp6=findViewById(R.id.otp6)


        image.setOnClickListener{
            Toast.makeText(this,"SignUp Failed",Toast.LENGTH_LONG).show()
            finish()
        }

        val intent=intent
        val y=   intent.getStringExtra("key")
        "$x  $y".also { editText.text = it }

        resend.setOnClickListener{
            Toast.makeText(this,"Password Sent Successfully",Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener{
            val otp=StringBuilder().append(otp1.text.toString()).append(otp2.text.toString())
                .append(otp3.text.toString()).append(otp4.text.toString())
                .append(otp5.text.toString()).append(otp6.text.toString())
            //val otp=pinView.text.toString()
            if(otp.length<6)
                Toast.makeText(this,"Enter Correct Otp",Toast.LENGTH_SHORT).show()
            else
            {
                val intent=Intent(this,GridActivity::class.java)
                Toast.makeText(this,"Yours Correct Otp is $otp",Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
        }


        otp1.addTextChangedListener(textWatcher1())


        otp2.addTextChangedListener(textWatcher2())


        otp3.addTextChangedListener(textWatcher3())


        otp4.addTextChangedListener(textWatcher4())


        otp5.addTextChangedListener(textWatcher5())


    }

    private fun textWatcher5() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (otp5.text.toString().length == 1)
                otp6.requestFocus()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    private fun textWatcher4() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (otp4.text.toString().length == 1)
                otp5.requestFocus()
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