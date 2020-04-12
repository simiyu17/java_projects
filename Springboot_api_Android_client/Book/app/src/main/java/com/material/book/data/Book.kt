package com.material.book.data

import com.google.gson.annotations.SerializedName




data class Book(val id: Long, val title: String, val author: String)

data class BookList (@SerializedName("books" )val books: List<Book>)

data class BookEmbedded (@SerializedName("_embedded" ) val list: BookList)