package com.ukrida.uasmobile.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ukrida.uasmobile.databinding.BookListBinding
import com.ukrida.uasmobile.gui.DetailBookActivity
import com.ukrida.uasmobile.gui.DetailBookActivity.Companion.EXTRA_DATA
import com.ukrida.uasmobile.model.BookModel

class BookAdapter(private val bookList: List<BookModel>): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(var bookListBinding: BookListBinding): RecyclerView.ViewHolder(bookListBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bookListBinding = BookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bookListBinding)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val books = bookList[position]

        holder.bookListBinding.apply {
            tvTitle.text = books.title
            tvSummary.text = books.summary
            tvInsertedAt.text = books.insertedAt


            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailBookActivity::class.java)
                intent.putExtra(EXTRA_DATA, books)
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}
