package com.ukrida.uasmobile.gui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ukrida.uasmobile.R
import com.ukrida.uasmobile.adapter.FavoriteAdapter
import com.ukrida.uasmobile.database.BookEntity
import com.ukrida.uasmobile.databinding.ActivityFavoriteBookBinding
import com.ukrida.uasmobile.viewmodel.FavoriteViewModel
import com.ukrida.uasmobile.viewmodel.ViewModelFactory

class FavoriteBookActivity : AppCompatActivity() {

    private var _favoriteBookBind: ActivityFavoriteBookBinding? = null
    private val favoriteBookBind get() = _favoriteBookBind

    private lateinit var favoriteViewModel: FavoriteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _favoriteBookBind = ActivityFavoriteBookBinding.inflate(layoutInflater)
        setContentView(favoriteBookBind?.root)

        favoriteViewModel = obtainViewModel(this)

        favoriteViewModel.getAllFavoriteBook().observe(this) { favoriteList ->
            setFavoriteList(favoriteList)
        }

        val binding = favoriteBookBind?.rvFavorite
        binding?.layoutManager = LinearLayoutManager(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.favorite)
    }

    private fun setFavoriteList(books: List<BookEntity>) {
        val favoriteBooks = ArrayList<BookEntity>()

        for (book in books) {
            favoriteBooks.clear()
            favoriteBooks.addAll(books)
        }

        val favoriteAdapter = FavoriteAdapter(favoriteBooks)
        favoriteBookBind?.rvFavorite?.adapter = favoriteAdapter
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
