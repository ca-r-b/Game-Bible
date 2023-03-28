package com.mobdeve.s12.aquino.batac.game_bible

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.aquino.batac.game_bible.databinding.RowItemCatalogBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class HomeAdapter(val data: ArrayList<Game>): RecyclerView.Adapter<HomeAdapter.HomeVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val itemBinding = RowItemCatalogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return HomeVH(itemBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.bindData(data[position])

        /* TODO
             1. See if ITEM is in BOOKMARKS / isBookmarked
             2. Set image of item accordingly
        */

        holder.itemHomeBinding.homeContainerLayout.setOnClickListener{
            Toast.makeText(it.context, "Clicked ${data[position].title}", Toast.LENGTH_SHORT).show()

            var intent = Intent(it.context, GameDetailsActivity::class.java)

            intent.putExtra("title", data[position].title)
            intent.putExtra("img1", data[position].img1)
            intent.putExtra("desc", data[position].desc)
            intent.putExtra("genre", data[position].genre)
            intent.putExtra("releaseDate", data[position].releaseDate)
            intent.putExtra("developer", data[position].developer)
            intent.putExtra("publisher", data[position].publisher)
            intent.putExtra("trailer", data[position].trailer)

            it.context.startActivity(intent)
        }

        holder.itemHomeBinding.homeSaveIv.setOnClickListener{
            Toast.makeText(it.context, "Saved ${data[position].title}", Toast.LENGTH_SHORT).show()

            /*TODO
                1. Add/Remove data to/from User's library (Bookmarks)
                2. Change image resource
            */
            holder.itemHomeBinding.homeSaveIv.setImageResource(R.drawable.ic_library_add_check)
        }
    }

    inner class HomeVH(val itemHomeBinding: RowItemCatalogBinding): RecyclerView.ViewHolder(itemHomeBinding.root){
        fun bindData(game: Game){
            itemHomeBinding.homeTitleTv.text = game.title
            itemHomeBinding.homeGenreTv.text = game.genre
            itemHomeBinding.homePicIv.setImageResource(game.img1)
//            TODO: Replace Image Resource of bookmark if part of saved
//            itemCatalogBinding.homeSaveIv.setImageResource(R.drawable.ic_library_add_check)
        }

    }
}