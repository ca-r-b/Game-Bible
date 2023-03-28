package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Show tool bar back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//       TODO: Change Profile Picture Functionality
//       TODO: Edit Biography Functionality
    }

//  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}