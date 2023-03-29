package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import android.graphics.Color
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
//      supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//      Hide action bar
        supportActionBar?.hide()

        binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addRevCancelBtn.setOnClickListener{
            var intent = Intent(this, ViewReviewActivity::class.java)

            startActivity(intent)
            finish()
        }

//        TODO: Save Changes + Add to Database
//        TODO: Activate Save Changes when there are changes to edit text + recommendation
        binding.addRevSaveBtn.setOnClickListener {
//            var intent = Intent(this, )
//            startActivity()
//            TODO: Try apply yung ActivityLauncher thing dito + Return to View Review
            var intent = Intent(this, ViewReviewActivity::class.java)

            startActivity(intent)
            finish()
        }

//        TODO: Can only select 1 button from the recommendation
//        TODO: This variable is for testing the recommend button only
        var check = true

        binding.addRevRecoBtn.setOnClickListener {
            // TODO: When editing, if recommended, color must be changed
            if(check){
                binding.addRevRecoBtn.setBackgroundColor(Color.GRAY)
                check = false
            }else{
                binding.addRevRecoBtn.setBackgroundColor(Color.rgb(102, 192, 244))
                check = true
            }
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