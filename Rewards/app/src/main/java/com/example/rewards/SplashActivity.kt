package com.example.rewards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.firstproject.DashboardActivity
import com.example.firstproject.R
import com.example.view.MapsActivity
import com.example.view.MatchActivity
import com.example.view.NotesActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashActivity : AppCompatActivity() {

    private lateinit var mFirebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mFirebaseAuth = FirebaseAuth.getInstance()
        val user=mFirebaseAuth.currentUser
        navigateToNext(user)
    }

    private fun navigateToNext(user: FirebaseUser?) {
        Handler(Looper.getMainLooper()).postDelayed({
            if(user!=null){
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }else{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
            finish()}
        }, 2000)
    }
}