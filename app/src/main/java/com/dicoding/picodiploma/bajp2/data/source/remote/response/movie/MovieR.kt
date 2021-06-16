package com.dicoding.picodiploma.bajp2.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieR(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<Movie>,

    @field:SerializedName("total_results")
    val totalResults: Int
)

data class Movie(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double
)