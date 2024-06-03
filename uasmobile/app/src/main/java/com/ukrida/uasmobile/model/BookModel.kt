package com.ukrida.uasmobile.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookModel(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("year")
    val year: String? = null,

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("inserted_at")
    val insertedAt: String? = null

) : Parcelable
