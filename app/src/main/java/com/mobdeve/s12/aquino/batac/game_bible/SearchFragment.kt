package com.mobdeve.s12.aquino.batac.game_bible

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
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

//      TODO: Setup Searching - Current code is subject to change after DB application
        binding.searchBarSv.setOnQueryTextListener(object: OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    var newDataSet = DataHelper.searchGame(query, gameList)
                    binding.searchRecyclerView.adapter = HomeAdapter(newDataSet)
                }else{
                    binding.searchRecyclerView.adapter = adapter
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    var newDataSet = DataHelper.searchGame(newText, gameList)
                    binding.searchRecyclerView.adapter = HomeAdapter(newDataSet)
                }else{
                    binding.searchRecyclerView.adapter = adapter
                }

                return true
            }
        })

        var spinnerSearch = ""

//      TODO: Setup Filter (Spinner) - Current code is subject to change after DB application
        binding.searchFilterSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Leave function blank as there is always an option selected
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerSearch = parent?.getItemAtPosition(position).toString()
                filterGenre(spinnerSearch)
            }
        }

        return binding.root
    }

    fun filterGenre(query: String){
        if(query != "All"){
            var newDataSet = DataHelper.searchGenre(query, gameList)
            binding.searchRecyclerView.adapter = HomeAdapter(newDataSet)
        }else{
            binding.searchRecyclerView.adapter = adapter
        }
    }

}