package com.example.mysubmission11.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysubmission11.adapter.UserAdapter
import com.example.mysubmission11.data.response.User
import com.example.mysubmission11.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUsers.layoutManager = LinearLayoutManager(requireActivity())
        val itemDecoration = DividerItemDecoration(requireActivity(), LinearLayoutManager(requireActivity()).orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        userViewModel.isLoading.observe(requireActivity()) { bool -> showLoading(bool) }

        userViewModel.nama.observe(requireActivity()) { listUser -> setUserData(listUser) }

        searchBarViewHandler()
    }

    private fun setUserData(user: List<User>) {
        val adapter = UserAdapter(requireActivity())
        adapter.submitList(user)
        binding.rvUsers.adapter = adapter
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(item: User) {
                val toDetailFragment = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                toDetailFragment.nama = item.login
                toDetailFragment.gambar = item.avatarUrl
                findNavController().navigate(toDetailFragment)
            }
        })
    }

    private fun searchBarViewHandler() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                searchBar.setText(searchView.text)
                searchView.hide()
                userViewModel.findUser(searchView.text.toString())
                false
            }
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