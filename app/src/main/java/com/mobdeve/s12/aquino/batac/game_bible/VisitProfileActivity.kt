package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityVisitProfileBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.DataHelper

class VisitProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisitProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVisitProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var username = intent.getStringExtra("username")
        var userList = DataHelper.loadSampleData3()

        var user = DataHelper.searchUser(username!!, userList)

        binding.visitImgIv.setImageResource(user!!.img)
        binding.visitNameTv.text = user.username
        binding.visitBioTv.text = user.bio
    }

    //  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}