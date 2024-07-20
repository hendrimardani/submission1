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
import com.example.mysubmission11.adapter.UserFollowingAdapter
import com.example.mysubmission11.data.response.UserFollowersFollowing
import com.example.mysubmission11.databinding.FragmentFollowingBinding
import com.example.mysubmission11.ui.UserFollowingViewModel

class FollowingFragment : Fragment() {
    private lateinit var binding: FragmentFollowingBinding
    private val userFollowingViewModel by viewModels<UserFollowingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowing.layoutManager = LinearLayoutManager(requireActivity())
        val itemDecoration = DividerItemDecoration(requireActivity(), LinearLayoutManager(requireActivity()).orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)

        val dataNama = arguments?.getString(EXTRA_USER)
        Log.e("TEST TEST", dataNama.toString())

        userFollowingViewModel.isLoading.observe(requireActivity()) { bool -> showLoading(bool)}
        userFollowingViewModel.namaFollowing.observe(requireActivity()) { userFollowersFollowing ->
            setUserData(userFollowersFollowing)
        }
        userFollowingViewModel.findUserFollowing(dataNama!!)
    }

    private fun setUserData(userFollowing: List<UserFollowersFollowing>) {
        val adapter = UserFollowingAdapter(requireActivity())
        adapter.submitList(userFollowing)
        binding.rvFollowing.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}