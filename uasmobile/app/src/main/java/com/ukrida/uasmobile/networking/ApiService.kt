package com.ukrida.uasmobile.networking

import com.ukrida.uasmobile.model.BookModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("books")
    fun getBookList() : Call<List<BookModel>>

    @FormUrlEncoded
    @POST("books")
    fun addBook(
        @Field("title") title: String,
        @Field("author") author: String,
        @Field("year") year: String,
        @Field("summary") summary: String
    ): Call<BookModel>

    @GET("books")
    fun searchBookTitle(
        @Query("title") title: String
    ): Call<List<BookModel>>
}
