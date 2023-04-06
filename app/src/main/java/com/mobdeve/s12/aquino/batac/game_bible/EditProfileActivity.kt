package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private lateinit var dbReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var uid: String

    private var imageURL: String? = null
    private var uri: Uri? = null

    private var imgResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                uri = data!!.data
                binding.editProfileImgIv.setImageURI(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Show tool bar back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//      TODO: Replace img and bio with current data
        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()

        getUserDetails()

//      TODO: Change Profile Picture Functionality
        binding.editProfileImgBtn.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)

            photoPicker.type = "image/*"

            imgResultLauncher.launch(photoPicker)
        }

//       TODO: Save Profile Functionality
        binding.editSaveBtn.setOnClickListener {

        }
    }

    private fun getUserDetails(){
        dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")

        dbReference.child(uid).get().addOnSuccessListener {
            if(it.exists()){
                val bio = it.child("bio").value
                val img = it.child("img").value

                if(img.toString() == "New"){
                    binding.editProfileImgIv.setImageResource(R.drawable.sample_default)
                }
//              TODO: Else when there exists photo of user

                binding.editBioEt.setText(bio.toString())

            }else{
                Toast.makeText(this, "Error: User does not exist!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "There could be an error.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateUserData(){

    }

//  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}