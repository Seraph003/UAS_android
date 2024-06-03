package com.ukrida.uasmobile.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ukrida.uasmobile.repository.BookRepository

class FavoriteViewModel(application: Application): ViewModel() {

    private val bookRepository: BookRepository = BookRepository(application)

    fun getAllFavoriteBook() = bookRepository.getAllFavoriteBook()
}