package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide action bar
        supportActionBar?.hide()
    }
}