package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

//        Hide action bar
        supportActionBar?.hide()

//        TODO: No SQLite Implementation (For Beta Testing)
        binding.loginLogBtn.setOnClickListener(View.OnClickListener {

            val email = binding.loginEmailEt.text.toString()
            val pass = binding.loginPassEt.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
//                TODO: check if user exists
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        var intent = Intent(this@LoginActivity, MainActivity::class.java)
                        Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show()

                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "Invalid Credentials! Please double check your inputs.", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Kindly double check your inputs.", Toast.LENGTH_SHORT).show()
            }

        })

        binding.loginRegBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(this@LoginActivity, RegisterActivity::class.java)

            /*
                TODO:
                    1. Add logic for checking inputted credentials
                        a. Display TOAST / SNACK BAR [*VALID*] if PASS
                        b. Else (if FAIL), show TOAST / SNACK BAR [*INVALID*]
            */

            startActivity(intent)
        })
    }

    override fun onStart() {
        super.onStart()
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}