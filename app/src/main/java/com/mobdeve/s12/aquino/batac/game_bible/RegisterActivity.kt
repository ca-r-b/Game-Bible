package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobdeve.s12.aquino.batac.game_bible.adapter.HomeAdapter
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityRegisterBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Game
import com.mobdeve.s12.aquino.batac.game_bible.model.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
        Log.d("DB REFERENCE", dbReference.toString())

        // Hide action bar
        supportActionBar?.hide()

        binding.regRegBtn.setOnClickListener {
            val email = binding.regEmailEt.text.toString()
            val username = binding.regUsernameEt.text.toString()
            val pass = binding.regPassEt.text.toString()
            val conPass = binding.regConPassEt.text.toString()

            validateEntries(email, username, pass, conPass)
        }

        binding.regLoginBtn.setOnClickListener {
            finish()
        }
    }
    private fun validateEntries(email: String, username: String, pass: String, conPass: String){
        var exists = false

        if(email.isNotEmpty() && username.isNotEmpty() && pass.isNotEmpty() && conPass.isNotEmpty()){
            if(username.length >= 5 && pass.length >= 8){
                dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            for(user in snapshot.children){
                                val userToAdd = user.getValue(User::class.java)
                                if(userToAdd?.username == username){
                                    exists = true
                                    Log.d("INSIDE FOR LOOP", exists.toString())
                                    break
                                }
                            }

                            if(!exists){
                                if(pass == conPass){
                                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                                        if(it.isSuccessful){
                                            var uid = firebaseAuth.uid.toString()

                                            val newUser = User(uid, email, username, pass, "Hello, there! My name's $username!", "New")

                                            dbReference.child(uid).setValue(newUser)
                                                .addOnCompleteListener {
                                                    Toast.makeText(applicationContext, "Account Created Successfully!", Toast.LENGTH_SHORT).show()
                                                }.addOnFailureListener { it ->
                                                    Toast.makeText(applicationContext, "Error! ${it.message}", Toast.LENGTH_SHORT).show()
                                                }

                                            finish()
                                        }else{
                                            Toast.makeText(applicationContext, "Email is already taken!", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }else{
                                    Toast.makeText(applicationContext, "Password does not match!", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(applicationContext, "Username already exists!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

            }else{
                Toast.makeText(this, "The username / password might be too short.", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Kindly double check your inputs.", Toast.LENGTH_SHORT).show()
        }
    }
}
