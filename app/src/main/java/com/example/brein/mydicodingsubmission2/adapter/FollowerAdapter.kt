package com.example.brein.mydicodingsubmission2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brein.mydicodingsubmission2.databinding.ItemRowUserBinding
import com.example.brein.mydicodingsubmission2.model.ItemUser

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    private val userFollower = ArrayList<ItemUser>()


    class FollowerViewHolder(private val followerBinding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(followerBinding.root) {
        fun bindFollower(itemUser: ItemUser) {
            followerBinding.apply {
                Glide.with(itemView.context).load(itemUser.avatar_url).into(userAva)
                userUsername.text = itemUser.username
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(
            ItemRowUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bindFollower(userFollower[position])
    }

    override fun getItemCount(): Int {
        return userFollower.size
    }

    internal fun setFollower(value: ArrayList<ItemUser>) {
        userFollower.clear()
        userFollower.addAll(value)
        notifyDataSetChanged()
    }
}