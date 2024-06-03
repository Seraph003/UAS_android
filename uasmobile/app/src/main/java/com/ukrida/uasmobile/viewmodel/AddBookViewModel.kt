package com.ukrida.uasmobile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ukrida.uasmobile.model.BookModel
import com.ukrida.uasmobile.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddBookViewModel: ViewModel() {

    fun addBook(title: String, author: String, year: String, summary: String) {
        val client = ApiConfig.getApiService().addBook(title, author, year, summary)
        client.enqueue(object : Callback<BookModel> {
            override fun onResponse(call: Call<BookModel>, response: Response<BookModel>) {
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BookModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    companion object {
        const val TAG = "Book_View_Model"
    }
}
