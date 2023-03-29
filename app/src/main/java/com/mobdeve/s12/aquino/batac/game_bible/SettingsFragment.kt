package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        binding.setProfileMenu.setOnClickListener {
            var intent = Intent(it.context, EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.setSavedMenu.setOnClickListener {
            var intent = Intent(it.context, BookmarkActivity::class.java)
            startActivity(intent)
        }

        binding.setAccountMenu.setOnClickListener {
            var intent = Intent(it.context, ChangePassActivity::class.java)
            startActivity(intent)
        }

        binding.setFBMenu.setOnClickListener {
            var intent = Intent(it.context, FacebookActivity::class.java)
            startActivity(intent)
        }

        binding.setLogoutMenu.setOnClickListener {
            var intent = Intent(it.context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finishAffinity()
        }

        return binding.root
    }
}