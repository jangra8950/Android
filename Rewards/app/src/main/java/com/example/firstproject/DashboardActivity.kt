package com.example.firstproject

import android.app.Activity
import com.example.firstproject.R
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.rewards.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class DashboardActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var txt1: TextView
    private lateinit var txt2: TextView
    private lateinit var btn: Button
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var signInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        image = findViewById(R.id.view)
        txt1 = findViewById(R.id.name)
        txt2 = findViewById(R.id.email)
        btn = findViewById(R.id.googleOut)

        mFirebaseAuth = FirebaseAuth.getInstance()
        if (mFirebaseAuth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }



        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(this, gso)

        setProfile()

        btn.setOnClickListener {
            signOut()
        }

//        image.setOnClickListener{
//            startCrop(getUserPhotoUrl())
//        }
    }


    private fun setProfile() {
        Glide.with(this).load(getUserPhotoUrl()).into(image)
        txt1.text = getUserName()
        txt2.text=getEmail()

    }

    private fun signOut() {
        mFirebaseAuth.signOut()
        signInClient.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun getUserPhotoUrl(): Uri? {
        val user = mFirebaseAuth.currentUser
        return if (user != null && user.photoUrl != null) {
            user.photoUrl
           // user.photoUrl.toString()
        } else null
    }

    private fun getUserName(): String? {
        val user = mFirebaseAuth.currentUser
        return if (user != null) {
            user.displayName
        } else "ANONYMOUS"
    }



    private fun getEmail(): String?
    {
        val user = mFirebaseAuth.currentUser
        return if (user != null) {
            user.email
        } else "ANONYMOUS"
    }

    private fun startCrop(imageUri: Uri?) {
        CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
            .start(this);
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if ( result.data !=null){
            val resu: CropImage.ActivityResult = CropImage.getActivityResult(result.data)
            Glide.with(this).load(resu.uri).into(image)}
        }
    }
}