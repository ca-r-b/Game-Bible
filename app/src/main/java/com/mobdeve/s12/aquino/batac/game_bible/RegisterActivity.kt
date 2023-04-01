package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Hide action bar
        supportActionBar?.hide()

//        TODO: No SQLite Implementation (For Beta Testing)
        binding.regRegBtn.setOnClickListener {
            val email = binding.regEmailEt.text.toString()
            val username = binding.regUsernameEt.text.toString()
            val pass = binding.regPassEt.text.toString()
            val conPass = binding.regConPassEt.text.toString()

            if(email.isNotEmpty() && username.isNotEmpty() && pass.isNotEmpty() && conPass.isNotEmpty()){
//                TODO: check if username exists
                if(pass == conPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Password does not match!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Kindly double check your inputs.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.regLoginBtn.setOnClickListener {
            finish()
        }
    }
}