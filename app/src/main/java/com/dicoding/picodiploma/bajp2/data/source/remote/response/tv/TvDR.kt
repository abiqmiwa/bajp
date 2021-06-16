package com.dicoding.picodiploma.bajp2.data.source.remote.response.tv

import com.google.gson.annotations.SerializedName

data class TvDR(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("genres")
    val genres: List<Genre>,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double
)

data class Genre(
    @field:SerializedName("name")
    val name: String,
)