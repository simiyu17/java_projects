package com.material.book.adapter

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.material.book.R
import com.material.book.api.BookApiClient
import com.material.book.data.Book
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_book.view.*

class BookAdapter(val context: Context) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    val client by lazy { BookApiClient.create() }
    var books: ArrayList<Book> = ArrayList()

    init { refreshBooks() }

    class BookViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.BookViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_book, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.view.name.text = books[position].title
        holder.view.btnDelete.setOnClickListener { showDeleteDialog(holder, books[position]) }
        holder.view.btnEdit.setOnClickListener { showUpdateDialog(holder, books[position]) }
    }

    override fun getItemCount() = books.size

  /*  fun refreshSamples() {
        books.clear()

        books.add(Book(0, "Guardians of the Galaxy","Simiyu" ))
        books.add(Book(1, "Avengers: Infinity War", "Dan"))
        books.add(Book(2,"Thor: Ragnorok", "Wanjala"))

        notifyDataSetChanged()
    }*/

    fun refreshBooks() {
        client.getBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                books.clear()
                books.addAll(result)
                notifyDataSetChanged()
            },{ error ->
               // Toast.makeText(context, "Refresh error: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e("ERRORS", error.message)
            })
    }

    fun updateBook(book: Book) {
        client.updateBook(book.id, book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ refreshBooks() }, { throwable ->
                Toast.makeText(context, "Update error: ${throwable.message}", Toast.LENGTH_LONG).show()
            })
    }

    fun addBook(book: Book) {
        client.addBook(book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ refreshBooks() }, { throwable ->
                Toast.makeText(context, "Add error: ${throwable.message}", Toast.LENGTH_LONG).show()
            })
    }

    fun deleteBook(book: Book) {
        client.deleteBook(book.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ refreshBooks() }, { throwable ->
                Toast.makeText(context, "Delete error: ${throwable.message}", Toast.LENGTH_LONG).show()
            })
    }

    fun showUpdateDialog(holder: BookViewHolder, book: Book) {
        val dialogBuilder = AlertDialog.Builder(holder.view.context)

        val input = EditText(holder.view.context)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp
        input.setText(book.title)

        dialogBuilder.setView(input)

        dialogBuilder.setTitle("Update Movie")
        dialogBuilder.setPositiveButton("Update") { dialog, whichButton ->
            updateBook(Book(book.id,input.text.toString(), "Dan"))
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, whichButton ->
            dialog.cancel()
        }
        val b = dialogBuilder.create()
        b.show()
    }

    fun showDeleteDialog(holder: BookViewHolder, book: Book) {
        val dialogBuilder = AlertDialog.Builder(holder.view.context)
        dialogBuilder.setTitle("Delete")
        dialogBuilder.setMessage("Confirm delete?")
        dialogBuilder.setPositiveButton("Delete") { dialog, whichButton ->
            deleteBook(book)
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, whichButton ->
            dialog.cancel()
        }
        val b = dialogBuilder.create()
        b.show()
    }
}
