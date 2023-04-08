package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityChangePassBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.User

class ChangePassActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChangePassBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePassBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Show tool bar back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()
        dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")

        uid = firebaseAuth.currentUser?.uid.toString()

        binding.passSaveBtn.setOnClickListener {
            saveCredentials()
        }
    }

    private fun saveCredentials(){
        val currentUser = firebaseAuth.currentUser
        val oldPass : String = binding.passOldEt.text.toString()
        val newPass1 : String = binding.passNew1Et.text.toString()
        val newPass2 : String = binding.passNew2Et.text.toString()

        if(oldPass.isNotEmpty() && newPass1.isNotEmpty() && newPass2.isNotEmpty()){
            if(newPass1 == newPass2 && newPass1.length >= 8){

                dbReference.child(uid).get()
                    .addOnSuccessListener {
                        if(it.exists()){
                            val foundEmail = it.child("email").value.toString()
                            val foundUsername = it.child("username").value.toString()
                            val foundPass = it.child("password").value.toString()
                            val foundBio = it.child("bio").value.toString()
                            val foundImg = it.child("img").value.toString()

                            val updatedUser = User(uid, foundEmail, foundUsername, newPass1, foundBio, foundImg)

                            if(oldPass == foundPass){
                                val credentials = EmailAuthProvider
                                    .getCredential(foundEmail, oldPass)

                                currentUser!!.reauthenticate(credentials).addOnCompleteListener { it ->
                                    if(it.isSuccessful){
                                        currentUser.updatePassword(newPass1)
                                        dbReference.child(uid).setValue(updatedUser)
                                        Toast.makeText(this, "New password has been saved!", Toast.LENGTH_SHORT).show()

                                        binding.passOldEt.text?.clear()
                                        binding.passNew1Et.text?.clear()
                                        binding.passNew2Et.text?.clear()
                                    }
                                }
                            }else{
                                Toast.makeText(this, "Old password does not match with the current.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, "There could be an error.", Toast.LENGTH_SHORT).show()
                    }

            }else{
                Toast.makeText(this,
                    "New and confirm passwords do not match.",
                    Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,
                "Kindly double-check your inputs.",
                Toast.LENGTH_SHORT).show()
        }
    }

//  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}