package com.ukrida.uasmobile.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ukrida.uasmobile.databinding.BookListBinding
import com.ukrida.uasmobile.gui.DetailBookActivity
import com.ukrida.uasmobile.database.BookEntity
import com.ukrida.uasmobile.gui.DetailBookActivity.Companion.EXTRA_DATA
import com.ukrida.uasmobile.model.BookModel

class FavoriteAdapter(private val favoriteBooks: List<BookEntity>): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(var favoriteBookBind: BookListBinding): RecyclerView.ViewHolder(favoriteBookBind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val favoriteBookBind = BookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(favoriteBookBind)
    }

    override fun getItemCount(): Int = favoriteBooks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorite = favoriteBooks[position]

        val books = BookModel (
            favorite.id,
            favorite.title,
            favorite.author,
            favorite.year,
            favorite.summary,
            favorite.insertedAt
        )

        holder.favoriteBookBind.apply {
            tvTitle.text = favorite.title
            tvSummary.text = favorite.summary
            tvInsertedAt.text = favorite.insertedAt

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailBookActivity::class.java)
                intent.putExtra(EXTRA_DATA, books)
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}