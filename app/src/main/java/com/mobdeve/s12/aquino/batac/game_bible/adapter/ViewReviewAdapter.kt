package com.mobdeve.s12.aquino.batac.game_bible.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.aquino.batac.game_bible.R
import com.mobdeve.s12.aquino.batac.game_bible.VisitProfileActivity
import com.mobdeve.s12.aquino.batac.game_bible.databinding.RowItemReviewBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Review

class ViewReviewAdapter (val data: ArrayList<Review>): RecyclerView.Adapter<ViewReviewAdapter.ViewReviewVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewReviewVH {
        val itemBinding = RowItemReviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewReviewVH(itemBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewReviewVH, position: Int) {
        holder.bindData(data[position])

//      TODO: Code is subject to change after Firebase implementation
        holder.itemReviewBinding.reviewUserIv.setOnClickListener{
            var intent = Intent(it.context, VisitProfileActivity::class.java)
            intent.putExtra("username", data[position].username)

            it.context.startActivity(intent)
        }
    }

    inner class ViewReviewVH(val itemReviewBinding: RowItemReviewBinding): RecyclerView.ViewHolder(itemReviewBinding.root){
        fun bindData(review: Review){
            itemReviewBinding.reviewUserTv.text = review.username
            itemReviewBinding.reviewCommentTv.text = review.comment

//          TODO: Set image according to user (USE FIREBASE)
            itemReviewBinding.reviewUserIv.setImageResource(R.drawable.sample_default)

//          TODO: Code is subject to change after implementation of Firebase
            if(review.doesRecommend == 1)
                itemReviewBinding.reviewRecomTv.text = "RECOMMENDED"
            else
                itemReviewBinding.reviewRecomTv.text = "NOT RECOMMENDED"
        }

    }
}