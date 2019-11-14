package com.example.movielistdemo.Repo

import com.example.movielistdemo.models.MovieList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CoroutineRestfulAPI {

    @GET("?apikey=49161480&")
    fun getMovieList(
        @Query("s") id: String = "Batman",
        @Query("page") page: String
    ): Deferred<MovieList>
}