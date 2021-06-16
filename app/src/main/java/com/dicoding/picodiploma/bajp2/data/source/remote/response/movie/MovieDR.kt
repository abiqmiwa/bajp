package com.dicoding.picodiploma.bajp2.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieDR(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,
)

data class GenresItem(
    @field:SerializedName("name")
    val name: String
)