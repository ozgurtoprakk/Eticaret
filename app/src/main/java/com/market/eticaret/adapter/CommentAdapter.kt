package com.market.eticaret.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.market.eticaret.databinding.RecyclerItem2Binding
import com.market.eticaret.model.Review
import com.market.eticaret.view.DetailFragmentArgs


class CommentAdapter(var review:ArrayList<Review>):RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(val binding: RecyclerItem2Binding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding= RecyclerItem2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return review.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binding.ratingBar.rating=review[position].rating!!.toFloat()
        holder.binding.commentText.text=review[position].comment.toString()

    }
}