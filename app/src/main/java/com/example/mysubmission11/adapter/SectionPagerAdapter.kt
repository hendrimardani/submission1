package com.example.mysubmission11.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mysubmission11.FollowersFragment
import com.example.mysubmission11.FollowingFragment

class SectionPagerAdapter(fragment: Fragment, val userName: String) : FragmentStateAdapter(fragment) {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowersFragment()
//                fragment.arguments = Bundle().apply {
//                    putString(EXTRA_USER, userName)
//                }
            }
            1 -> {
                fragment = FollowingFragment()
//                fragment.arguments = Bundle().apply {
//                    putString(EXTRA_USER, userName)
//                }
            }
        }
        return fragment as Fragment
    }
}