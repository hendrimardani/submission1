package com.example.mysubmission11.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysubmission11.data.response.UserFollowersFollowing
import com.example.mysubmission11.databinding.ItemFollowersBinding

class UserFollowingAdapter(val context: Context): ListAdapter<UserFollowersFollowing, UserFollowingAdapter.ViewHolder>(DIFF_CALLBACK){

    inner class ViewHolder(val context: Context, val binding: ItemFollowersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userDetail: UserFollowersFollowing) {
            Glide.with(context)
                .load("${userDetail.avatarUrl}")
                .into(binding.ivItemFollowers)
            binding.tvNameItemFollowers.text = userDetail.login
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserFollowersFollowing>() {
            override fun areItemsTheSame(oldItem: UserFollowersFollowing, newItem: UserFollowersFollowing): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserFollowersFollowing, newItem: UserFollowersFollowing): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}