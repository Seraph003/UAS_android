package com.ukrida.uasmobile.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBookToFavorite(bookEntity: BookEntity)

    @Query("DELETE FROM favorite_books WHERE favorite_books.id = :id")
    fun deleteBookFromFavorite(id: String?)

    @Query("SELECT * FROM favorite_books WHERE favorite_books.id = :id")
    fun checkFavoriteStatus(id: String?): LiveData<List<BookEntity>>

    @Query("SELECT * FROM favorite_books ORDER BY id")
    fun getAllFavoriteBook(): LiveData<List<BookEntity>>

}