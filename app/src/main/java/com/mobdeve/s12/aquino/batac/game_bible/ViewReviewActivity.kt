package com.mobdeve.s12.aquino.batac.game_bible

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobdeve.s12.aquino.batac.game_bible.adapter.HomeAdapter
import com.mobdeve.s12.aquino.batac.game_bible.adapter.ViewReviewAdapter
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityViewReviewBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Game
import com.mobdeve.s12.aquino.batac.game_bible.model.Review

class ViewReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewReviewBinding
    private lateinit var adapter: ViewReviewAdapter

    private lateinit var dbReviewRef: DatabaseReference
    private var gid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbReviewRef = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Reviews")

//      Setup - Recycler view variables
        val layoutManager = LinearLayoutManager(this)
        gid = intent.getIntExtra("gid", 0)

//      Setup - Fetch data for Recycler view
        loadReviews()
        binding.viewRevRV.layoutManager = layoutManager

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.viewRevFAB.setOnClickListener {
            var intent = Intent(this, AddReviewActivity::class.java)
            intent.putExtra("gid", gid)

            startActivity(intent)
            finish()
        }
    }

    private fun loadReviews(){
        dbReviewRef.addListenerForSingleValueEvent(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                var totalRevCount = 0
                var recCount = 0
                var reviewList = ArrayList<Review>()

                if(snapshot.exists()){
                    for(review in snapshot.children){
                        val reviewToAdd = review.getValue(Review::class.java)
                        if(reviewToAdd?.gid == gid){
                            reviewList.add(reviewToAdd)
                            totalRevCount++
                            if(reviewToAdd.doesRecommend == 1){
                                recCount++
                            }
                        }
                    }

                    adapter = ViewReviewAdapter(this@ViewReviewActivity, reviewList)
                    binding.viewRevRV.adapter = adapter
                    binding.viewRevStatsTv.text = "$recCount out of $totalRevCount user reviews recommend this game"
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

//  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}