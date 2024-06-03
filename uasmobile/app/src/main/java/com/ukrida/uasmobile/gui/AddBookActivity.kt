package com.ukrida.uasmobile.gui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ukrida.uasmobile.R
import com.ukrida.uasmobile.databinding.ActivityAddBookBinding
import com.ukrida.uasmobile.viewmodel.AddBookViewModel

class AddBookActivity : AppCompatActivity() {

    private var _addBookBind: ActivityAddBookBinding? = null
    private val addBookBind get() = _addBookBind

    private val addBookViewModel by viewModels<AddBookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _addBookBind = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(addBookBind?.root)

        addBook()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.add_book)
    }

    private fun addBook() {

        addBookBind?.btnAddBook?.setOnClickListener {

            val title = addBookBind?.edtTitle?.text.toString()
            val author = addBookBind?.edtAuthor?.text.toString()
            val year = addBookBind?.edtYear?.text.toString()
            val summary = addBookBind?.edtSummary?.text.toString()

            when {
                title.isEmpty() -> addBookBind?.edtTitle?.error = getString(R.string.error_empty_field)
                author.isEmpty() -> addBookBind?.edtAuthor?.error = getString(R.string.error_empty_field)
                year.isEmpty() -> addBookBind?.edtYear?.error = getString(R.string.error_empty_field)
                summary.isEmpty() -> addBookBind?.edtSummary?.error = getString(R.string.error_empty_field)


                else ->  {

                    addBookViewModel.addBook(title, author, year, summary)

                    AlertDialog.Builder(this).apply {
                        setTitle(R.string.success)
                        setMessage(R.string.success_message)
                        setPositiveButton(R.string.back) { _, _ ->
                            startActivity(Intent(this@AddBookActivity, MainActivity::class.java))
                            finish()
                        }
                        create()
                        show()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
