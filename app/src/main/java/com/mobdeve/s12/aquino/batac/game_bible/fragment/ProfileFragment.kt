package com.mobdeve.s12.aquino.batac.game_bible.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.aquino.batac.game_bible.R
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var dbReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var uid: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()

        getUserDetails()

        return binding.root
    }

    private fun getUserDetails(){
        dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")

        dbReference.child(uid).get()
            .addOnSuccessListener {
                if(it.exists()){
                    val username = it.child("username").value
                    val bio = it.child("bio").value
                    val img = it.child("img").value

                    binding.profileNameTv.text = username.toString()
                    binding.profileBioTv.text = bio.toString()

                    if(img.toString() == "New"){
                        binding.profileImgIv.setImageResource(R.drawable.sample_default)
                    } //TODO Replace image with url
                }else{
                    Toast.makeText(context, "Error: User does not exist!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(context, "There could be an error.", Toast.LENGTH_SHORT).show()
            }

    }

}