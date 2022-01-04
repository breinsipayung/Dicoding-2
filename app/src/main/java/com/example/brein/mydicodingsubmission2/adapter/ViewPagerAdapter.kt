package com.example.brein.mydicodingsubmission2.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.brein.mydicodingsubmission2.fragment.FollowerFragment
import com.example.brein.mydicodingsubmission2.fragment.FollowingFragment

class ViewPagerAdapter(activity: AppCompatActivity , private val item: Array<String> , private val login : Bundle) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return item.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when(position){
            0 -> FollowerFragment()
            1 -> FollowingFragment()
            else -> FollowingFragment()
        }
        fragment.arguments = login
        return fragment
    }


}