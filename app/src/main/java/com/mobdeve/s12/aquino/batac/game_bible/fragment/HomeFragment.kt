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

    private lateinit var binding: FragmentHomeBinding

    private lateinit var dbReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Games")

        val layoutManager1 = LinearLayoutManager(context)
        val layoutManager2 = LinearLayoutManager(context)

        loadCatalog()
        binding.homeRecyclerView.layoutManager = layoutManager1
        binding.homeCommRecyclerView.layoutManager = layoutManager2

        return binding.root
    }

    private fun loadCatalog(){
        dbReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var gameList1 = ArrayList<Game>()
                var gameList2 = ArrayList<Game>()
                if(snapshot.exists()){
                    for(game in snapshot.children){
                        val gameToAdd = game.getValue(Game::class.java)
                        if(gameToAdd?.section == "Modern Classics"){
                            gameList1.add(gameToAdd)
                        }else if (gameToAdd?.releaseDate?.takeLast(4) == "2022"){
                            gameList2.add(gameToAdd!!)
                        }
                    }
                    adapter1 = HomeAdapter(context, gameList1)
                    adapter2 = HomeAdapter(context, gameList2)
                    binding.homeRecyclerView.adapter = adapter1
                    binding.homeCommRecyclerView.adapter = adapter2
                    binding.homeLoad1.visibility = View.GONE
                    binding.homeLoad2.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

}

