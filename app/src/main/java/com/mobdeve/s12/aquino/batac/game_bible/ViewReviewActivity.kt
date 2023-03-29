package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityViewReviewBinding

class ViewReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        TODO: Setup RecyclerView + Add Adapter + Linear Layout Manager
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.viewRevFAB.setOnClickListener {
            var intent = Intent(this, AddReviewActivity::class.java)

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