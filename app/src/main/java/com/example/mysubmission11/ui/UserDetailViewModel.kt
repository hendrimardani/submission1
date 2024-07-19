package com.example.mysubmission11.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysubmission11.data.response.UserDetail
import com.example.mysubmission11.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    companion object {
        private const val TAG = "TEST HASIL RESPONSE"
        private const val QUERY_USER = "hendri"
    }

    private val _namaQueryUser = MutableLiveData<UserDetail>()
    val namaQueryUser: LiveData<UserDetail> = _namaQueryUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        findUserDetail(namaQueryUser.toString())
    }

    fun findUserDetail(queryUser: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(queryUser)
        client.enqueue(object : Callback<UserDetail> {
            override fun onResponse(
                call: Call<UserDetail>,
                response: Response<UserDetail>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) _namaQueryUser.value = response.body()
                else Log.e(TAG, "onFailure : ${response.message()}")
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${t.message}")
            }
        })
    }
}