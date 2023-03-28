package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityAddReviewBinding
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityViewReviewBinding

class AddReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      Show tool bar back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addRevCancelBtn.setOnClickListener{
            finish()
        }

//        TODO: Save Changes + Add to Database
//        TODO: Activate Save Changes when there are changes to edit text + recommendation
//        TODO: Can only select 1 button from the recommendation
    }

//  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}