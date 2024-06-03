package com.ukrida.uasmobile.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ukrida.uasmobile.database.BookDao
import com.ukrida.uasmobile.database.BookDatabase
import com.ukrida.uasmobile.database.BookEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BookRepository(application: Application) {
    private val bookDao: BookDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = BookDatabase.bookDatabase(application)
        bookDao = db.bookDao()
    }

    fun  addBookToFavorite(bookEntity: BookEntity) {
        executorService.execute {
            bookDao.addBookToFavorite(bookEntity)
        }

    }

    fun deleteBookFromFavorite(id: String?) {
        executorService.execute {
            bookDao.deleteBookFromFavorite(id)
        }
    }

    fun checkFavoriteStatus(id: String?): LiveData<List<BookEntity>> = bookDao.checkFavoriteStatus(id)

    fun getAllFavoriteBook():LiveData<List<BookEntity>> = bookDao.getAllFavoriteBook()
}
