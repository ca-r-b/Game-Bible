package com.mobdeve.s12.aquino.batac.game_bible

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentBookmarkBinding
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentSearchBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.DataHelper
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class SearchFragment : Fragment() {

    private lateinit var adapter: HomeAdapter
    private lateinit var gameList: ArrayList<Game>
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

//      TODO: Setup Recycler View
        gameList = DataHelper.loadData()
        adapter = HomeAdapter(gameList)

        val layoutManager = LinearLayoutManager(context)

        binding.searchRecyclerView.layoutManager = layoutManager
        binding.searchRecyclerView.setHasFixedSize(true)
        binding.searchRecyclerView.adapter = adapter

        return binding.root
    }

}