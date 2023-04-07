package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityVisitProfileBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.User

class VisitProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisitProfileBinding

    private lateinit var dbUserRef: DatabaseReference
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVisitProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        uid = intent.getStringExtra("uid").toString()

        getUserDetails()
    }

    private fun getUserDetails(){
        dbUserRef = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")

        dbUserRef.child(uid).get()
            .addOnSuccessListener {
                if(it.exists()){
                    val username = it.child("username").value
                    val bio = it.child("bio").value
                    val img = it.child("img").value

                    binding.visitNameTv.text = username.toString()
                    binding.visitBioTv.text = bio.toString()

                    if(img.toString() == "New"){
                        binding.visitImgIv.setImageResource(R.drawable.sample_default)
                    }else{
                        Glide.with(this).load(img).into(binding.visitImgIv)
                    }
                }else{
                    Toast.makeText(this, "Error: User does not exist!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "There could be an error.", Toast.LENGTH_SHORT).show()
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