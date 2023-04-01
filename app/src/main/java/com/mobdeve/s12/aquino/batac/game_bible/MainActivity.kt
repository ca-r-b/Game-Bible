package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        this.setTitle("Home")

//      Set DEFAULT Fragment
        replaceFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){

                R.id.homeNavItem -> {
                    replaceFragment(HomeFragment())
                    this.title = "Home"
                }

                R.id.searchNavItem -> {
                    replaceFragment(SearchFragment())
                    this.title = "Search and Filter"
                }

                R.id.profileNavItem -> {
                    replaceFragment(ProfileFragment())
                    this.title = "Your Profile"
                }

                R.id.menuNavItem -> {
                    replaceFragment(SettingsFragment())
                    this.title = "Menu and Settings"
                }

            }

            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}