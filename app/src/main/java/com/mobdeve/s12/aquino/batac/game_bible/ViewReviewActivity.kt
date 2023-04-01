package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityViewReviewBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.DataHelper
import com.mobdeve.s12.aquino.batac.game_bible.model.Review

class ViewReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewReviewBinding
    private lateinit var adapter: ViewReviewAdapter
    private lateinit var reviews: ArrayList<Review>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        val title = intent.getStringExtra("title").toString()
        reviews = DataHelper.searchReviews(title, DataHelper.loadSampleData2())

//        TODO: Setup DATASET
        adapter = ViewReviewAdapter(reviews)
        binding.viewRevRV.adapter = adapter
        binding.viewRevRV.layoutManager = layoutManager

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.viewRevStatsTv.text = DataHelper.getRecos(reviews).toString() + " out of " + reviews.size + " user reviews recommend this game"

        binding.viewRevFAB.setOnClickListener {
            var intent = Intent(this, AddReviewActivity::class.java)
            intent.putExtra("title", title)
//            Toast.makeText(this, title, Toast.LENGTH_SHORT).show()

            startActivity(intent)
            finish()
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