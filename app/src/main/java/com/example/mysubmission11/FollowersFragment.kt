package com.example.mysubmission11

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysubmission11.adapter.SectionPagerAdapter.Companion.EXTRA_USER
import com.example.mysubmission11.adapter.UserFollowersAdapter
import com.example.mysubmission11.data.response.UserFollowersFollowing
import com.example.mysubmission11.databinding.FragmentFollowersBinding
import com.example.mysubmission11.ui.UserFollowersViewModel

class FollowersFragment : Fragment() {
    private lateinit var binding: FragmentFollowersBinding
    private val userFollowersViewModel by viewModels<UserFollowersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowers.layoutManager = LinearLayoutManager(requireActivity())
        val itemDecoration = DividerItemDecoration(requireActivity(), LinearLayoutManager(requireActivity()).orientation)
        binding.rvFollowers.addItemDecoration(itemDecoration)

        val dataNama = arguments?.getString(EXTRA_USER)
        Log.e("TEST TEST", dataNama.toString())

        userFollowersViewModel.isLoading.observe(requireActivity()) { bool -> showLoading(bool) }
        userFollowersViewModel.namaFollowers.observe(requireActivity()) { userFollowersFollowing ->
            setUserData(userFollowersFollowing)
        }
        userFollowersViewModel.findUserFollowers(dataNama!!)
    }

    private fun setUserData(userFollowers: List<UserFollowersFollowing>) {
        val adapter = UserFollowersAdapter(requireActivity())
        adapter.submitList(userFollowers)
        binding.rvFollowers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}