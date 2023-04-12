package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityEditProfileBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.User
import java.io.ByteArrayOutputStream

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private lateinit var dbReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var uid: String

    private var imageURL: String? = "New"
    private var uri: Uri? = null

    private var imgResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                uri = data!!.data

                binding.editProfileImgIv.setImageURI(uri)

                imageURL = uid
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Show tool bar back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()

        getUserDetails()

        binding.editProfileImgBtn.setOnClickListener {
            changeImage()
        }

        binding.editSaveBtn.setOnClickListener {
            saveDetails()
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
                }else{
                    Glide.with(this).load(img).into(binding.editProfileImgIv)
                }

                binding.editBioEt.setText(bio.toString())
            }else{
                Toast.makeText(this, "Error: User does not exist!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "There could be an error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveDetails(){
        if(binding.editBioEt.text.toString().length in 5..200){
            val bio = binding.editBioEt.text.toString()

            dbReference.child(uid).get().addOnSuccessListener {
                if(it.exists()){
                    val email = it.child("email").value
                    val username = it.child("username").value
                    val password = it.child("password").value

                    if(imageURL != "New"){
                        storageReference = FirebaseStorage.getInstance("gs://game-bible-fecc0.appspot.com/")
                            .reference.child("UserPfps/$imageURL")

                        storageReference.putFile(uri!!).addOnSuccessListener {
                            storageReference.downloadUrl.addOnSuccessListener { it ->
                                // Since function is asynchronous, updating must be done inside the said function
                                imageURL = it.toString()
                                val updatedUser = User(uid,
                                    email.toString(),
                                    username.toString(),
                                    password.toString(),
                                    bio,
                                    imageURL)

                                dbReference.child(uid).setValue(updatedUser)
                                Toast.makeText(this, "Changes are saved!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        val updatedUser = User(uid,
                            email.toString(),
                            username.toString(),
                            password.toString(),
                            bio,
                            "New")

                        dbReference.child(uid).setValue(updatedUser)
                        Toast.makeText(this, "Changes are saved!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Error: User does not exist!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "There could be an error.", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Biography must have 5 - 200 characters.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun changeImage(){
        val gallery = Intent(Intent.ACTION_PICK)

        gallery.type = "image/*"
        imgResultLauncher.launch(gallery)
    }

//  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}