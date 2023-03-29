package com.mobdeve.s12.aquino.batac.game_bible

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentBookmarkBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.DataHelper
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class BookmarkFragment : Fragment() {

    private lateinit var adapter: BookmarkAdapter
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var gameList: ArrayList<Game>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(layoutInflater)

//      TODO: Replace RecyclerView
        gameList = DataHelper.loadData()
        adapter = BookmarkAdapter(gameList)

        val layoutManager = LinearLayoutManager(context)

        binding.bmRecyclerView.layoutManager = layoutManager
        binding.bmRecyclerView.setHasFixedSize(true)
        binding.bmRecyclerView.adapter = adapter

        return binding.root
    }

}