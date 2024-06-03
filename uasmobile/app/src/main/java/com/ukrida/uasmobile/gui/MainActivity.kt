package com.ukrida.uasmobile.gui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ukrida.uasmobile.R
import com.ukrida.uasmobile.adapter.BookAdapter
import com.ukrida.uasmobile.databinding.ActivityMainBinding
import com.ukrida.uasmobile.model.BookModel
import com.ukrida.uasmobile.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var _mainBind: ActivityMainBinding? = null
    private val mainBind get() = _mainBind

    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mainBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBind?.root)

        mainViewModel.bookList.observe(this) { bookList ->
            setBookList(bookList)
        }

        val binding = mainBind?.rvMain
        binding?.layoutManager = LinearLayoutManager(this)

        supportActionBar?.title = getString(R.string.bookshelf)

        mainBind?.fabAddBookMenu?.setOnClickListener {
            startActivity(Intent(this, AddBookActivity::class.java))
        }
    }

    private fun setBookList(books: List<BookModel>) {
        val listBook = ArrayList<BookModel>()

        for (book in books) {
            listBook.clear()
            listBook.addAll(books)
        }

        val bookAdapter = BookAdapter(listBook)
        mainBind?.rvMain?.adapter = bookAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search_menu)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.searchBookTitle(query)
                mainBind?.rvMain?.visibility = View.VISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.favorite_menu -> {
            startActivity(Intent(this, FavoriteBookActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
