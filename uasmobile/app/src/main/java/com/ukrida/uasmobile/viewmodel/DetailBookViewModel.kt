package com.ukrida.uasmobile.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ukrida.uasmobile.database.BookEntity
import com.ukrida.uasmobile.repository.BookRepository

class DetailBookViewModel(application: Application): ViewModel() {

    private val bookRepository: BookRepository = BookRepository(application)


    fun addBookToFavorite(bookEntity: BookEntity) = bookRepository.addBookToFavorite(bookEntity)
    fun deleteBookFromFavorite(id: String?) = bookRepository.deleteBookFromFavorite(id)
    fun checkFavoriteStatus(id: String?) = bookRepository.checkFavoriteStatus(id)
}