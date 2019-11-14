package com.example.movielistdemo.models

import java.io.Serializable

data class MovieList(
    var Response: String? = "",
    var Search: List<Search?>? = listOf(),
    var totalResults: String? = ""
)

data class Search(
    var Poster: String? = "",
    var Title: String? = "",
    var Type: String? = "",
    var Year: String? = "",
    var imdbID: String? = ""
):Serializable