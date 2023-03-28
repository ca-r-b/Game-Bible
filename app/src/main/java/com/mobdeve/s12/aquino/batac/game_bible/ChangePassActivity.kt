package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityChangePassBinding

class ChangePassActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChangePassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePassBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Show tool bar back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//      TODO: Add logic of changing passwords
    }

//  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}