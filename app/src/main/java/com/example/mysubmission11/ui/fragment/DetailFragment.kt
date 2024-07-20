package com.example.mysubmission11.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mysubmission11.R
import com.example.mysubmission11.adapter.SectionPagerAdapter
import com.example.mysubmission11.data.response.UserDetail
import com.example.mysubmission11.databinding.FragmentDetailBinding
import com.example.mysubmission11.ui.viewmodel.UserDetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val userDetailViewModel by viewModels<UserDetailViewModel>()
    private lateinit var dataUsername: String

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.text_1,
            R.string.text_2
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataUsername = DetailFragmentArgs.fromBundle(arguments as Bundle).nama
        val dataImage = DetailFragmentArgs.fromBundle(arguments as Bundle).gambar

        binding.tvNameDetail.text = dataUsername
        Glide.with(requireActivity())
            .load("$dataImage")
            .into(binding.ivDetail)

        userDetailViewModel.findUserDetail(dataUsername)
        userDetailViewModel.namaQueryUser.observe(viewLifecycleOwner) {  userDetail ->
            getOutput(userDetail)
        }

        setUpTabLayout(dataUsername)
    }

    private fun getOutput(userDetail: UserDetail) {
        binding.tvNameDetail.text = dataUsername
        binding.tvUsernameDetail.text = userDetail.name
        binding.tvFollowersDetail.text = "${userDetail.followers} Followers"
        binding.tvFollowingDetail.text = "${userDetail.following} Following"
    }

    private fun setUpTabLayout(dataUsername: String) {
        val sectionPagerAdapter = SectionPagerAdapter(this, dataUsername)
        val viewPager2: ViewPager2= requireView().findViewById(R.id.view_pager2)
        viewPager2.adapter = sectionPagerAdapter
        val tabLayout: TabLayout = requireView().findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}