package com.material.book

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.material.book.adapter.BookAdapter
import com.material.book.data.Book
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = BookAdapter(this)

        book_list.layoutManager = LinearLayoutManager(this)
        book_list.adapter = adapter

        fab.setOnClickListener{ showNewDialog() }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.refresh, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int = item!!.itemId
        if(id == R.id.refresh){
            adapter.refreshBooks()
            Toast.makeText(this.baseContext, "Refreshed", Toast.LENGTH_LONG).show()
            true
        }
        else {
            super.onOptionsItemSelected(item)
        }

        return true
    }

    fun showNewDialog() {
        val dialogBuilder = AlertDialog.Builder(this)

        val input = EditText(this@MainActivity)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp

        dialogBuilder.setView(input)

        dialogBuilder.setTitle("New Book")
        dialogBuilder.setMessage("Enter Name Below")
        dialogBuilder.setPositiveButton("Save") { dialog, whichButton ->
            adapter.addBook(Book(0,input.text.toString(),"Dan"))
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, whichButton ->
            //pass
        }
        val b = dialogBuilder.create()
        b.show()
    }


    fun showUpdateDialog(holder: BookAdapter.BookViewHolder, book: Book) {
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
            adapter.updateBook(Book(book.id,input.text.toString(), "Simiyu"))
        }
        dialogBuilder.setNegativeButton("Cancel", { dialog, whichButton ->
            dialog.cancel()
        })
        val b = dialogBuilder.create()
        b.show()
    }

    fun showDeleteDialog(holder: BookAdapter.BookViewHolder, book: Book) {
        val dialogBuilder = AlertDialog.Builder(holder.view.context)
        dialogBuilder.setTitle("Delete")
        dialogBuilder.setMessage("Confirm delete?")
        dialogBuilder.setPositiveButton("Delete") { dialog, whichButton ->
            adapter.deleteBook(book)
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, whichButton ->
            dialog.cancel()
        }
        val b = dialogBuilder.create()
        b.show()
    }
}
