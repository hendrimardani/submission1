package com.example.mysubmission11.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysubmission11.data.response.UserFollowersFollowing
import com.example.mysubmission11.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFollowingViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val TAG = "TEST HASIL RESPONSE"
        private const val QUERY_USER = "hendri"
    }

    private val _namaFollowing = MutableLiveData<List<UserFollowersFollowing>>()
    val namaFollowing: LiveData<List<UserFollowersFollowing>> = _namaFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        findUserFollowing(QUERY_USER)
    }

    fun findUserFollowing(queryUser: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowingUser(queryUser)
        client.enqueue(object : Callback<List<UserFollowersFollowing>> {
            override fun onResponse(
                call: Call<List<UserFollowersFollowing>>,
                response: Response<List<UserFollowersFollowing>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful)  {
                    _namaFollowing.value = response.body()
                    Log.d(TAG, response.toString())
                }
                else Log.e(TAG, "onFailure : ${response.message()}")
            }

            override fun onFailure(call: Call<List<UserFollowersFollowing>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${t.message}")
            }
        })
    }
}