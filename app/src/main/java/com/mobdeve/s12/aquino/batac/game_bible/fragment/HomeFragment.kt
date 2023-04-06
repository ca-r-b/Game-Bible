package com.mobdeve.s12.aquino.batac.game_bible.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.mobdeve.s12.aquino.batac.game_bible.adapter.HomeAdapter
import com.mobdeve.s12.aquino.batac.game_bible.databinding.FragmentHomeBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class HomeFragment : Fragment() {

    private lateinit var adapter1: HomeAdapter
    private lateinit var adapter2: HomeAdapter

    private lateinit var gameList1: ArrayList<Game>
    private lateinit var gameList2: ArrayList<Game>

    private lateinit var binding: FragmentHomeBinding

    private lateinit var dbReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Games")

//      TODO Layout manager for RecyclerViews
        val layoutManager1 = LinearLayoutManager(context)
        val layoutManager2 = LinearLayoutManager(context)

//      TODO Setup 1st RecyclerView (All Games)
        gameList1 = ArrayList()
        loadCatalog()
        binding.homeRecyclerView.layoutManager = layoutManager1
        binding.homeRecyclerView.setHasFixedSize(true)

//      TODO Setup 2nd RecyclerView (Community Recommendations)

//      TODO Logic for changing arraylist
        gameList2 = ArrayList<Game>()

        adapter2 = HomeAdapter(context, gameList2)

        binding.homeCommRecyclerView.layoutManager = layoutManager2
        binding.homeCommRecyclerView.setHasFixedSize(true)
        binding.homeCommRecyclerView.adapter = adapter2

        return binding.root
    }

    private fun loadCatalog(){
        dbReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                gameList1.clear()
                if(snapshot.exists()){
                    for(game in snapshot.children){
                        val gameToAdd = game.getValue(Game::class.java)
                        if(gameToAdd?.section == "Featured"){
                            gameList1.add(gameToAdd)
                        }
                    }
                    adapter1 = HomeAdapter(context, gameList1)
                    binding.homeRecyclerView.adapter = adapter1
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

}

