package com.mobdeve.s12.aquino.batac.game_bible

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobdeve.s12.aquino.batac.game_bible.adapter.HomeAdapter
import com.mobdeve.s12.aquino.batac.game_bible.databinding.ActivityBookmarkBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Bookmark
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding
    private lateinit var adapter: HomeAdapter

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var currentUid = firebaseAuth.uid.toString()

    private lateinit var dbGameRef: DatabaseReference
    private lateinit var dbBookmarkRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        dbGameRef = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Games")
        dbBookmarkRef = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Bookmarks")

        loadCatalog()
        binding.bmRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bmRecyclerView.setHasFixedSize(true)
    }

    private fun loadCatalog(){
        dbGameRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var savedList = ArrayList<Game>()

                if(snapshot.exists()){
                    for(game in snapshot.children){
                        val gameData = game.getValue(Game::class.java)
                        val bookmarkId = currentUid + gameData?.gid
                        Log.d("Bookmark ID", bookmarkId)
                        dbBookmarkRef.child(bookmarkId).get().addOnSuccessListener {
                            if(it.exists()){
                                savedList.add(gameData!!)
                                adapter = HomeAdapter(this@BookmarkActivity, savedList)
                                binding.bmRecyclerView.adapter = adapter
                                Log.d("Add Game", gameData!!.title.toString())
                            }
                        }
                    }
//                    adapter = HomeAdapter(this@BookmarkActivity, savedList)
//                    binding.bmRecyclerView.adapter = adapter

                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    //  Finish activity after clicking back button on ToolBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}