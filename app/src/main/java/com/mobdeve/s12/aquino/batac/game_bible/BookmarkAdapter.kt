package com.mobdeve.s12.aquino.batac.game_bible

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.aquino.batac.game_bible.databinding.RowItemCatalogBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Game

class BookmarkAdapter(val data: ArrayList<Game>): RecyclerView.Adapter<BookmarkAdapter.BookmarkVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkAdapter.BookmarkVH {
        val itemBinding = RowItemCatalogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return BookmarkVH(itemBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BookmarkVH, position: Int) {
        holder.bindData(data[position])
    }

    inner class BookmarkVH(val itemCatalogBinding: RowItemCatalogBinding): RecyclerView.ViewHolder(itemCatalogBinding.root){
        fun bindData(game: Game){
            itemCatalogBinding.homeTitleTv.text = game.title
            itemCatalogBinding.homeGenreTv.text = game.genre
            itemCatalogBinding.homePicIv.setImageResource(game.img1)

//            TODO: Replace Image Resource of bookmark if part of saved
//            itemCatalogBinding.homeSaveIv.setImageResource(R.drawable.ic_library_add_check)
        }
    }
}