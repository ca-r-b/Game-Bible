package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Hide action bar
        supportActionBar?.hide()

        binding.loginLogBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(this@LoginActivity, MainActivity::class.java)

            /*
                TODO:
                    1. Add logic for checking inputted credentials
                        a. Move to next activity if PASS
                            i. Search for credentials in database (check if VALID)
                            ii. See if username and password exist / credentials match
                            iii. Pass username to next activity
                        b. Else (if FAIL), show TOAST / SNACK BAR
            */

            startActivity(intent)
            finish()
        })

        binding.loginRegBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(this@LoginActivity, RegisterActivity::class.java)

            /*
                TODO:
                    1. Add logic for checking inputted credentials
                        a. Display TOAST / SNACK BAR [*VALID*] if PASS
                        b. Else (if FAIL), show TOAST / SNACK BAR [*INVALID*]
            */

            startActivity(intent)
        })
    }
}