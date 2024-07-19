package com.example.mysubmission11.ui

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysubmission11.data.response.GithubUserResponse
import com.example.mysubmission11.data.response.User
import com.example.mysubmission11.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    companion object {
        private const val TAG = "TEST HASIL RESPONSE"
        private const val QUERY_USER = "hendri"
    }

    private val _nama = MutableLiveData<List<User>>()
    val nama: LiveData<List<User>> = _nama

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var totalCount = 0

    init {
        findUser(QUERY_USER)
    }

    fun findUser(queryUser: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(queryUser)
        client.enqueue(object : Callback<GithubUserResponse> {
            override fun onResponse(call: Call<GithubUserResponse>, response: Response<GithubUserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    totalCount = response.body()!!.totalCount
                    if (totalCount == 0) {
                        _nama.value = response.body()?.users
                        Toast.makeText(context, "DATA TIDAK ADA !", Toast.LENGTH_LONG).show()
                    } else {
                        _nama.value = response.body()?.users
                    }
                } else {
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${t.message}")
            }
        })
    }
}