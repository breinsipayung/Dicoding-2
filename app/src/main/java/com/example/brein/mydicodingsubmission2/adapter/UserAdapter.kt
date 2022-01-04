package com.example.brein.mydicodingsubmission2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.brein.mydicodingsubmission2.DetailUserActivity
import com.example.brein.mydicodingsubmission2.databinding.ItemRowUserBinding
import com.example.brein.mydicodingsubmission2.model.ItemUser

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder>() {

    private val listUserAdapter = ArrayList<ItemUser>()

    fun listUser(value: ArrayList<ItemUser>) {
        listUserAdapter.clear()
        listUserAdapter.addAll(value)
        notifyDataSetChanged()
    }

    inner class UserAdapterViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun adapterBinding(itemUser: ItemUser) {
            binding.apply {
                Glide.with(itemView.context).load(itemUser.avatar_url).apply(RequestOptions())
                    .into(userAva)
                userUsername.text = itemUser.username
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.DETAIL_USER, itemUser)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapterViewHolder, position: Int) {
        holder.adapterBinding(listUserAdapter[position])
    }

    override fun getItemCount(): Int {
        return listUserAdapter.size
    }

}