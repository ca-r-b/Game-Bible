package com.mobdeve.s12.aquino.batac.game_bible

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentHomeBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.DataHelper
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class HomeFragment : Fragment() {

    private lateinit var adapter1: HomeAdapter
    private lateinit var adapter2: HomeAdapter

    private lateinit var gameList1: ArrayList<Game>
    private lateinit var gameList2: ArrayList<Game>

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

//      TODO Layout manager for RecyclerViews
        val layoutManager1 = LinearLayoutManager(context)
        val layoutManager2 = LinearLayoutManager(context)

//      TODO Setup 1st RecyclerView (All Games)
        gameList1 = DataHelper.searchSection("Featured", DataHelper.loadData())

        adapter1 = HomeAdapter(gameList1)

        binding.homeRecyclerView.layoutManager = layoutManager1
        binding.homeRecyclerView.setHasFixedSize(true)
        binding.homeRecyclerView.adapter = adapter1

//      TODO Setup 2nd RecyclerView (Community Recommendations)

//      TODO Logic for changing arraylist
        gameList2 = DataHelper.searchSection("Discovery Queue", DataHelper.loadData())

        adapter2 = HomeAdapter(gameList2)

        binding.homeCommRecyclerView.layoutManager = layoutManager2
        binding.homeCommRecyclerView.setHasFixedSize(true)
        binding.homeCommRecyclerView.adapter = adapter2

        return binding.root
    }

}