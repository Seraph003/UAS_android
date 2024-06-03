package com.ukrida.uasmobile.gui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ukrida.uasmobile.R
import com.ukrida.uasmobile.databinding.ActivityDetailBookBinding
import com.ukrida.uasmobile.database.BookEntity
import com.ukrida.uasmobile.model.BookModel
import com.ukrida.uasmobile.viewmodel.DetailBookViewModel
import com.ukrida.uasmobile.viewmodel.ViewModelFactory

class DetailBookActivity : AppCompatActivity() {

    private var _bookDetailBind: ActivityDetailBookBinding? = null
    private val bookDetailBind get() = _bookDetailBind

    private lateinit var detailBookViewModel: DetailBookViewModel

    private lateinit var bookEntity: BookEntity

    private lateinit var id: String

    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bookDetailBind = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(bookDetailBind?.root)

        val books = intent.getParcelableExtra(EXTRA_DATA) as? BookModel
        this.id = books?.id.toString()

        detailBookViewModel = obtainViewModel(this)

        setBookDetail()
        addToFavorite()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail)
    }

    private fun setBookDetail() {
        val books = intent.getParcelableExtra(EXTRA_DATA) as? BookModel
        bookDetailBind?.apply {
            tvTitle.text = books?.title
            tvAuthor.text = books?.author
            tvYear.text = books?.year
            tvSummary.text = books?.summary
            tvInsertedAt.text = books?.insertedAt
        }
    }

    private fun addToFavorite() {
        detailBookViewModel.checkFavoriteStatus(id).observe(this) { favoriteList ->
            isFavorite = favoriteList.isNotEmpty()
            if (isFavorite) {
                bookDetailBind?.fabFavorite?.setImageResource(R.drawable.ic_round_favorite)
            } else {
                bookDetailBind?.fabFavorite?.setImageResource(R.drawable.ic_round_favorite_border)
            }
        }

        val books = intent.getParcelableExtra(EXTRA_DATA) as? BookModel
        bookDetailBind?.fabFavorite?.setOnClickListener {
            if (!isFavorite) {
                isFavorite = true
                bookDetailBind?.fabFavorite?.setImageResource(R.drawable.ic_round_favorite)
                bookEntity = BookEntity(
                    books?.id.toString(),
                    books?.title.toString(),
                    books?.author.toString(),
                    books?.year.toString(),
                    books?.summary.toString(),
                    books?.insertedAt.toString()
                )

                detailBookViewModel.addBookToFavorite(bookEntity)

                Toast.makeText(this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show()
            } else {
                isFavorite = false
                bookDetailBind?.fabFavorite?.setImageResource(R.drawable.ic_round_favorite_border)
                detailBookViewModel.deleteBookFromFavorite(books?.id)
                Toast.makeText(this, R.string.removed_from_favorite, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailBookViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailBookViewModel::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_DATA = "Books_Data"
    }
}
