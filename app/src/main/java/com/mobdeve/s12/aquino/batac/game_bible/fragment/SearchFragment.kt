package com.mobdeve.s12.aquino.batac.game_bible.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.mobdeve.s12.aquino.batac.game_bible.adapter.HomeAdapter
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentSearchBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.DataHelper
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class SearchFragment : Fragment() {

    private lateinit var adapter: HomeAdapter
    private lateinit var gameList: ArrayList<Game>
    private lateinit var binding: FragmentSearchBinding

    private var query = ""
    private var spinnerSearch = ""

    private lateinit var dbReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Games")

//      Recycler view setup
//      ** Catalog will be loaded automatically with the given input fields **
        gameList = ArrayList()
        val layoutManager = LinearLayoutManager(context)
        binding.searchRecyclerView.layoutManager = layoutManager
        binding.searchRecyclerView.setHasFixedSize(true)

//      TODO: Setup Searching - Current code is subject to change after DB application
        binding.searchBarSv.setOnQueryTextListener(object: OnQueryTextListener{
            override fun onQueryTextSubmit(input: String?): Boolean {
                if(input != null){
                    query = input
                    searchGame()
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    query = newText
                    searchGame()
                }

                return true
            }
        })

//      TODO: Setup Filter (Spinner) - Current code is subject to change after DB application
        binding.searchFilterSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Leave function blank as there is always an option selected
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerSearch = parent?.getItemAtPosition(position).toString()
                searchGame()
            }
        }

        return binding.root
    }

    private fun searchGame(){
        var lowercaseInput = query.lowercase()
        var pattern = Regex(lowercaseInput)

        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gameList.clear()
                if(snapshot.exists()){
                    for(game in snapshot.children){
                        val gameToAdd = game.getValue(Game::class.java)
                        if(pattern.containsMatchIn(gameToAdd?.title!!.lowercase()) && (spinnerSearch == gameToAdd.genre || spinnerSearch == "All")){
                            gameList.add(gameToAdd!!)
                        }
                    }
                    adapter = HomeAdapter(context, gameList)
                    binding.searchRecyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

}