package com.mobdeve.s12.aquino.batac.game_bible.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.disklrucache.DiskLruCache.Value
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobdeve.s12.aquino.batac.game_bible.GameDetailsActivity
import com.mobdeve.s12.aquino.batac.game_bible.databinding.RowItemCatalogBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Bookmark
import com.mobdeve.s12.aquino.batac.game_bible.model.Game
import com.mobdeve.s12.aquino.batac.game_bible.R

class HomeAdapter(val context: Context?, val data: ArrayList<Game>): RecyclerView.Adapter<HomeAdapter.HomeVH>() {

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var currentUid = firebaseAuth.uid.toString()
    private lateinit var dbReference: DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val itemBinding = RowItemCatalogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return HomeVH(itemBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.bindData(data[position])
        if (context != null) {
            Glide.with(context).load(data[position].img).into(holder.itemHomeBinding.homePicIv)
        }

        holder.itemHomeBinding.homeContainerLayout.setOnClickListener{
            var intent = Intent(it.context, GameDetailsActivity::class.java)

            intent.putExtra("gid", data[position].gid)
            intent.putExtra("title", data[position].title)
            intent.putExtra("img", data[position].img)
            intent.putExtra("desc", data[position].desc)
            intent.putExtra("genre", data[position].genre)
            intent.putExtra("releaseDate", data[position].releaseDate)
            intent.putExtra("developer", data[position].developer)
            intent.putExtra("publisher", data[position].publisher)
            intent.putExtra("trailer", data[position].trailer)

            it.context.startActivity(intent)
        }

        holder.itemHomeBinding.homeSaveIv.setOnClickListener{
            dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Bookmarks")

            var game = data[position]
            var exists = false
            var bookmarkId = currentUid + game.gid

            dbReference.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (bookmark in snapshot.children) {
                            val bookmarkData = bookmark.getValue(Bookmark::class.java)
                            if(bookmarkData?.bid == bookmarkId){
//                              If exists, move to else statement & remove from bookmarks
                                exists = true
                                break
                            }
                        }

                        if(!exists){
                            var bookmarkId = currentUid + game.gid
                            var newBookmark = Bookmark(bookmarkId, currentUid, game.gid)

                            dbReference.child(bookmarkId).setValue(newBookmark).addOnCompleteListener {
                                Toast.makeText(context, "Saved ${game.title}!", Toast.LENGTH_SHORT).show()
                                holder.itemHomeBinding.homeSaveIv.setImageResource(R.drawable.ic_library_add_check)
                            }.addOnFailureListener {
                                Toast.makeText(context, "Error while trying to save ${game.title}!", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            dbReference.child(bookmarkId).removeValue()
                            Toast.makeText(context, "Removed ${game.title} from bookmarks!", Toast.LENGTH_SHORT).show()
                            holder.itemHomeBinding.homeSaveIv.setImageResource(R.drawable.ic_library_add)
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }

    inner class HomeVH(val itemHomeBinding: RowItemCatalogBinding): RecyclerView.ViewHolder(itemHomeBinding.root){
        fun bindData(game: Game) {
            dbReference = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Bookmarks")

            itemHomeBinding.homeTitleTv.text = game.title
            itemHomeBinding.homeGenreTv.text = game.genre

            dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (bookmark in snapshot.children) {
                            val bookmarkData = bookmark.getValue(Bookmark::class.java)
                            if (bookmarkData?.uid == currentUid && bookmarkData.gid == game.gid) {
                                itemHomeBinding.homeSaveIv.setImageResource(R.drawable.ic_library_add_check)
                                break
                            }
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }
}