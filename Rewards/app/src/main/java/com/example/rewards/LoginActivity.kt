package com.example.rewards


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.firstproject.DashboardActivity
import com.example.firstproject.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {

    private val numbers= arrayOf("+91","+95","+72","+65","+768")
    private lateinit var num:AutoCompleteTextView
    private lateinit var btn:TextView
    private lateinit var button: Button
    private lateinit var phone:EditText
    private lateinit var google:SignInButton
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var signInClient:GoogleSignInClient
    private val RC_SIGN_IN = 1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button=findViewById(R.id.btnLog)
        btn=findViewById(R.id.btnSign)
        phone=findViewById(R.id.ph)
        google=findViewById(R.id.sign_in_button)

        google.setOnClickListener{
            signIn()
        }

                //Then we need a GoogleSignInOptions object
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

              //Then we will get the GoogleSignInClient object from GoogleSignIn class
        signInClient = GoogleSignIn.getClient(this, gso)

        mFirebaseAuth = FirebaseAuth.getInstance()



        button.setOnClickListener{
            val number=phone.text.toString()
            val intent=Intent(this,OtpActivity::class.java)
            intent.putExtra("key",number)
            startActivity(intent)
        }
        btn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        num=findViewById(R.id.outlined_exposed_dropdown_editable)
        num.run {
            setOnClickListener {
                setAdapter(ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, numbers))
//                setTextColor(ContextCompat.getColor(context, R.color.primary_red))
            }
            inputType = InputType.TYPE_NULL
            performClick()

        }
    }


    private fun signIn() {
        val signInIntent: Intent = signInClient.signInIntent

        resultLauncher.launch(signInIntent)
       // startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val exception=task.exception
            if(task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)
                   // firebaseAuthWithGoogle(account)
                } catch (e: ApiException) {
                    Log.w("signInActivity", "Google sign in failed", e)
                }
            }else{
                Log.w("signIn Activity",exception.toString())
            }
        }
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            val exception=task.exception
//            if(task.isSuccessful) {
//                try {
//                    val account = task.getResult(ApiException::class.java)
//                    firebaseAuthWithGoogle(account)
//                } catch (e: ApiException) {
//                    Log.w("signInActivity", "Google sign in failed", e)
//                }
//            }else{
//                 Log.w("signIn Activity",exception.toString())
//            }
//        }
//    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mFirebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener(this) {
                Toast.makeText(this, "Successfully signIn.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
            .addOnFailureListener(this) { e: Exception? ->
                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
    }
}