package com.ukrida.uasmobile.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_books"
)
class BookEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("author")
    val author: String,

    @ColumnInfo("year")
    val year: String,

    @ColumnInfo("summary")
    val summary: String,

    @ColumnInfo("inserted_at")
    val insertedAt: String
)