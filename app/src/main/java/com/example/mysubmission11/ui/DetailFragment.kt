package com.example.mysubmission11.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mysubmission11.data.response.UserDetail
import com.example.mysubmission11.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val userDetailViewModel by viewModels<UserDetailViewModel>()

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

        val dataName = DetailFragmentArgs.fromBundle(arguments as Bundle).nama
        val dataImage = DetailFragmentArgs.fromBundle(arguments as Bundle).gambar

        binding.tvNameDetail.text = dataName
        Glide.with(requireActivity())
            .load("$dataImage")
            .into(binding.ivDetail)

        userDetailViewModel.findUserDetail(dataName)

        userDetailViewModel.isLoading.observe(requireActivity()) { bool -> showLoading(bool) }

        userDetailViewModel.namaQueryUser.observe(viewLifecycleOwner) {  userDetail ->
            binding.tvNameDetail.text = dataName
            binding.tvUsernameDetail.text = userDetail.name
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}