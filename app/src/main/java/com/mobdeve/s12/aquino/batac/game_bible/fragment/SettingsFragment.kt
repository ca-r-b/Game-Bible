package com.mobdeve.s12.aquino.batac.game_bible.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.s12.aquino.batac.game_bible.BookmarkActivity
import com.mobdeve.s12.aquino.batac.game_bible.ChangePassActivity
import com.mobdeve.s12.aquino.batac.game_bible.EditProfileActivity
import com.mobdeve.s12.aquino.batac.game_bible.LoginActivity
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.setProfileMenu.setOnClickListener {
            val intent = Intent(it.context, EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.setSavedMenu.setOnClickListener {
            val intent = Intent(it.context, BookmarkActivity::class.java)
            startActivity(intent)
        }

        binding.setAccountMenu.setOnClickListener {
            val intent = Intent(it.context, ChangePassActivity::class.java)
            startActivity(intent)
        }

        binding.setLogoutMenu.setOnClickListener {
            firebaseAuth.signOut()

            val intent = Intent(it.context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finishAffinity()
            Toast.makeText(context, "You have logged out.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}