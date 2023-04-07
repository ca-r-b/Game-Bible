package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobdeve.s12.aquino.batac.game_bible.adapter.HomeAdapter
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityAddReviewBinding
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityViewReviewBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Game
import com.mobdeve.s12.aquino.batac.game_bible.model.Review

class AddReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddReviewBinding

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var currentUid = firebaseAuth.uid.toString()
    private lateinit var dbReference: DatabaseReference // TODO Refer to Reviews (Look for user)

    private var gid: Int = 0
    private var rid: String = ""
    private var recommended = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      Hide action bar
        supportActionBar?.hide()

        binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Reviews")
        gid = intent.getIntExtra("gid", 0)
        rid = currentUid + gid

        checkReview()

        binding.addRevBackBtn.setOnClickListener{
            var i = Intent(this, ViewReviewActivity::class.java)
            i.putExtra("gid", gid)

            startActivity(i)
            finish()
        }

        binding.addRevSaveBtn.setOnClickListener {
            var characterCount = binding.addRevEt.text.toString().length
            if(characterCount in 5..100){
                saveReview()
            }else{
                Toast.makeText(this,
                    "Your review should have 5 to 100 characters.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.addRevRemoveBtn.setOnClickListener {
            removeReview()
        }

        binding.addRevRecoBtn.setOnClickListener {
            if (recommended){
                binding.addRevRecoBtn.setBackgroundColor(Color.GRAY)
                binding.addRevRecoBtn.text = "RECOMMENDED"
                recommended = false
            }else{
                binding.addRevRecoBtn.setBackgroundColor(Color.rgb(102, 192, 244))
                binding.addRevRecoBtn.text = "RECOMMEND THIS GAME"
                recommended = true
            }
        }
    }

    private fun checkReview(){
        dbReference.child(rid).get()
            .addOnSuccessListener {
                if(it.exists()){
                    val comment = it.child("comment").value
                    val doesRecommend = it.child("doesRecommend").value

                    if (doesRecommend.toString().toInt() == 1){
                        binding.addRevRecoBtn.setBackgroundColor(Color.GRAY)
                        binding.addRevRecoBtn.text = "RECOMMENDED"
                        recommended = false
                    }else{
                        binding.addRevRecoBtn.setBackgroundColor(Color.rgb(102, 192, 244))
                        binding.addRevRecoBtn.text = "RECOMMEND THIS GAME"
                    }

                    binding.addRevEt.setText(comment.toString())
                }else{
                    binding.addRevRemoveBtn.visibility = View.INVISIBLE
                }
            }.addOnFailureListener {
                Toast.makeText(this@AddReviewActivity, "There could be an error.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveReview(){
        val comment = binding.addRevEt.text.toString()

        var doesRecommend = 0
        if (binding.addRevRecoBtn.text == "RECOMMENDED"){
            doesRecommend = 1
        }

        dbReference.child(rid).get()
            .addOnSuccessListener {
                if(!it.exists()){
                    var newReview = Review(rid, gid, currentUid, comment, doesRecommend)

                    dbReference.child(rid).setValue(newReview).addOnCompleteListener {
                        Toast.makeText(this, "Review added!", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error while trying to save review!", Toast.LENGTH_SHORT).show()
                    }

                    binding.addRevRemoveBtn.visibility = View.VISIBLE
                }else{
                    var updatedReview = Review(rid, gid, currentUid, comment, doesRecommend)

                    dbReference.child(rid).setValue(updatedReview)
                    Toast.makeText(this, "Changes saved!", Toast.LENGTH_SHORT).show()

                    binding.addRevRemoveBtn.visibility = View.VISIBLE
                }
            }.addOnFailureListener {
                Toast.makeText(this@AddReviewActivity, "There could be an error.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun removeReview(){
        dbReference.child(rid).get()
            .addOnSuccessListener {
                if(it.exists()){
                    dbReference.child(rid).removeValue()

                    binding.addRevEt.text?.clear()
                    binding.addRevRecoBtn.setBackgroundColor(Color.rgb(102, 192, 244))
                    binding.addRevRecoBtn.text = "RECOMMEND THIS GAME"
                    binding.addRevRemoveBtn.visibility = View.INVISIBLE

                    Toast.makeText(this, "Your review has been removed!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this@AddReviewActivity, "There could be an error.", Toast.LENGTH_SHORT).show()
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