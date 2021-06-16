package com.dicoding.picodiploma.bajp2.api

import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.MovieDR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.MovieR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvDR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvR
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String
    ) : Call<MovieR>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id : String,
        @Query("api_key") apiKey: String
    ) : Call<MovieDR>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ) : Call<TvR>

    @GET("tv/{id}")
    fun getTvShowDetail(
        @Path("id") id : String,
        @Query("api_key") apiKey: String
    ) : Call<TvDR>

}