package com.ukrida.uasmobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ukrida.uasmobile.model.BookModel
import com.ukrida.uasmobile.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _bookList = MutableLiveData<List<BookModel>>()
    val bookList: LiveData<List<BookModel>> =_bookList

    init {
        getBookList()
    }

    private fun getBookList() {
        val client = ApiConfig.getApiService().getBookList()
        client.enqueue(object: Callback<List<BookModel>> {
            override fun onResponse(
                call: Call<List<BookModel>>,
                response: Response<List<BookModel>>
            ) {
                if (response.isSuccessful) {
                    _bookList.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun searchBookTitle(title: String) {
        val client = ApiConfig.getApiService().searchBookTitle(title)
        client.enqueue(object : Callback<List<BookModel>> {
            override fun onResponse(
                call: Call<List<BookModel>>,
                response: Response<List<BookModel>>
            ) {
                if (response.isSuccessful) {
                    _bookList.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        const val TAG = "Book_View_Model"
    }

}