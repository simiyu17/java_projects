package com.material.book.api

import com.material.book.data.Book
import com.material.book.data.BookEmbedded
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface BookApiClient {

    @GET("books/") fun getBooks(): Observable<List<Book>>
    @POST("books/") fun addBook(@Body book: Book): Completable
    @DELETE("books/{id}") fun deleteBook(@Path("id") id: Long) : Completable
    @PUT("books/{id}") fun updateBook(@Path("id")id: Long, @Body movie: Book) : Completable

    companion object {

        fun create(): BookApiClient {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.43.225:8082/api/")
                .build()

            return retrofit.create(BookApiClient::class.java)
        }
    }
}
