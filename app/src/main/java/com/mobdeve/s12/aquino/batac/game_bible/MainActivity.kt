package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityMainBinding
import com.mobdeve.s12.aquino.batac.game_bible.fragment.HomeFragment
import com.mobdeve.s12.aquino.batac.game_bible.fragment.ProfileFragment
import com.mobdeve.s12.aquino.batac.game_bible.fragment.SearchFragment
import com.mobdeve.s12.aquino.batac.game_bible.fragment.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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