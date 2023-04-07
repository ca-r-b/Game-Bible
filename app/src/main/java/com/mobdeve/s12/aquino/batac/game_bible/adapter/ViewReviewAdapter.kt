package com.mobdeve.s12.aquino.batac.game_bible.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.aquino.batac.game_bible.R
import com.mobdeve.s12.aquino.batac.game_bible.VisitProfileActivity
import com.mobdeve.s12.aquino.batac.game_bible.databinding.RowItemReviewBinding
import com.mobdeve.s12.aquino.batac.game_bible.model.Review

class ViewReviewAdapter (val context: Context?, val data: ArrayList<Review>): RecyclerView.Adapter<ViewReviewAdapter.ViewReviewVH>() {

    private var dbUserRef = FirebaseDatabase.getInstance("https://game-bible-fecc0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewReviewVH {
        val itemBinding = RowItemReviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewReviewVH(itemBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewReviewVH, position: Int) {
        holder.bindData(data[position])

//        When clicked, REDIRECT to user
        holder.itemReviewBinding.reviewUserIv.setOnClickListener{
            var intent = Intent(it.context, VisitProfileActivity::class.java)
            intent.putExtra("uid", data[position].uid)

            context?.startActivity(intent)
        }
    }

    inner class ViewReviewVH(val itemReviewBinding: RowItemReviewBinding): RecyclerView.ViewHolder(itemReviewBinding.root){
        fun bindData(review: Review){
            dbUserRef.child(review.uid.toString()).get()
                .addOnSuccessListener {
                    if(it.exists()){
                        val username = it.child("username").value
                        val img = it.child("img").value

                        itemReviewBinding.reviewUserTv.text = username.toString()
                        itemReviewBinding.reviewCommentTv.text = review.comment

                        if(review.doesRecommend == 1)
                            itemReviewBinding.reviewRecomTv.text = "RECOMMENDED"
                        else
                            itemReviewBinding.reviewRecomTv.text = "NOT RECOMMENDED"

                        if(img.toString() == "New"){
                            itemReviewBinding.reviewUserIv.setImageResource(R.drawable.sample_default)
                        }else{
                            Glide.with(context!!).load(img).into(itemReviewBinding.reviewUserIv)
                        }
                    }else{
                        Toast.makeText(context, "Error: User does not exist!", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(context, "There could be an error.", Toast.LENGTH_SHORT).show()
                }
        }

    }
}