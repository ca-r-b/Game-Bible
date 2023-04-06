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

        binding.loginLogBtn.setOnClickListener{
            val email = binding.loginEmailEt.text.toString()
            val pass = binding.loginPassEt.text.toString()

            validateLogin(email, pass)
        }

        binding.loginRegBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        })
    }

    private fun validateLogin(email: String, pass: String){
        if(email.isNotEmpty() && pass.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if(it.isSuccessful){
                    var intent = Intent(this, MainActivity::class.java)
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