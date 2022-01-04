package com.example.brein.mydicodingsubmission2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brein.mydicodingsubmission2.databinding.ItemRowUserBinding
import com.example.brein.mydicodingsubmission2.model.ItemUser

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private val userFollowing = ArrayList<ItemUser>()

    class FollowingViewHolder(private val followingBinding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(followingBinding.root) {
        fun bindFollowing(itemUser: ItemUser) {
            followingBinding.apply {
                Glide.with(itemView.context).load(itemUser.avatar_url).into(userAva)
                userUsername.text = itemUser.username
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        return FollowingViewHolder(
            ItemRowUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bindFollowing(userFollowing[position])
    }

    override fun getItemCount(): Int {
        return userFollowing.size
    }

    internal fun setFollowing(value: ArrayList<ItemUser>) {
        userFollowing.clear()
        userFollowing.addAll(value)
        notifyDataSetChanged()
    }
}