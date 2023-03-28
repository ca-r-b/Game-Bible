package com.mobdeve.s12.aquino.batac.game_bible

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentHomeBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.DataHelper
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class HomeFragment : Fragment() {

    private lateinit var adapter: HomeAdapter
    private lateinit var gameList: ArrayList<Game>

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        gameList = DataHelper.loadData()
        adapter = HomeAdapter(gameList)

        val layoutManager = LinearLayoutManager(context)

        binding.homeRecyclerView.layoutManager = layoutManager
        binding.homeRecyclerView.setHasFixedSize(true)
        binding.homeRecyclerView.adapter = adapter

        return binding.root
    }

}